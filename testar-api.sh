#!/usr/bin/env bash

BASE="${BASE:-http://localhost:8080}"
S=$RANDOM
OK=0; FAIL=0

req() {
  local esperado="$1" metodo="$2" caminho="$3" corpo="$4" resp
  if [ -n "$corpo" ]; then
    resp=$(curl -s -w $'\n%{http_code}' -X "$metodo" "$BASE$caminho" -H 'Content-Type: application/json' -d "$corpo")
  else
    resp=$(curl -s -w $'\n%{http_code}' -X "$metodo" "$BASE$caminho")
  fi
  CODE="${resp##*$'\n'}"; BODY="${resp%$'\n'*}"
  if [ "$CODE" = "$esperado" ]; then OK=$((OK+1)); m="✅"; else FAIL=$((FAIL+1)); m="❌ (esperado $esperado)"; fi
  printf '%s %-6s %-48s -> %s\n' "$m" "$metodo" "$caminho" "$CODE"
  [ "$CODE" != "$esperado" ] && echo "     $BODY" | cut -c1-300
}
id() { echo "$BODY" | python3 -c 'import sys,json; print(json.load(sys.stdin).get("id",""))' 2>/dev/null; }

questao() {
  req 201 POST /api/questoes '{"numero":'$1',"enunciado":"Questão '$1' de teste","letraCorreta":"B","prova":{"id":'$2'},"topico":{"id":'$3'},"alternativas":[{"letra":"A","texto":"opção A"},{"letra":"B","texto":"opção B"},{"letra":"C","texto":"opção C"},{"letra":"D","texto":"opção D"}]}'
}

echo "############ FASE 1: POVOAR A BASE ############"
echo "== Usuário =="
req 201 POST /api/usuarios '{"nomeUsuario":"aluno'$S'","email":"aluno'$S'@teste.com","senha":"123456","dataNascimento":"2008-05-10","escola":"Escola Teste","serie":"3ª Série"}'
U=$(id)
echo "== Área > Disciplina > Tópico =="
req 201 POST /api/areas '{"nome":"Exatas '$S'"}';                                   A=$(id)
req 201 POST /api/disciplinas '{"nome":"Matemática","area":{"id":'$A'}}';           D=$(id)
req 201 POST /api/topicos '{"nome":"Funções","disciplina":{"id":'$D'}}';            T=$(id)
echo "== Prova > Questões (com alternativas) =="
req 201 POST /api/provas '{"ano":2025,"serie":"TERCEIRA_SERIE","tempoMinutos":120}'; P=$(id)
questao 1 $P $T; Q1=$(id)
questao 2 $P $T; Q2=$(id)
questao 3 $P $T; Q3=$(id)
echo "Base criada: usuario=$U area=$A disciplina=$D topico=$T prova=$P questoes=$Q1,$Q2,$Q3"

echo; echo "############ FASE 2: TESTAR ############"
echo "== Usuário =="
req 200 GET  /api/usuarios
req 200 GET  /api/usuarios/$U
req 200 PUT  /api/usuarios/$U '{"nomeUsuario":"aluno'$S'","email":"aluno'$S'@teste.com","senha":"654321","dataNascimento":"2008-05-10","escola":"Escola Nova","serie":"3ª Série"}'
req 404 GET  /api/usuarios/999999
req 400 POST /api/usuarios '{"nomeUsuario":"","email":"invalido","senha":"","dataNascimento":"2999-01-01","escola":"","serie":""}'
req 400 POST /api/usuarios '{"nomeUsuario":"x'$S'","email":"x'$S'@t.com","senha":"1","dataNascimento":"2008-05-10","escola":"E","serie":"4ª Série"}'

echo "== Área / Disciplina / Tópico =="
req 200 GET  /api/areas
req 200 GET  /api/areas/$A
req 200 GET  /api/disciplinas
req 200 GET  /api/disciplinas/$D
req 200 GET  /api/topicos
req 200 GET  /api/topicos/$T
req 200 PUT  /api/topicos/$T '{"id":'$T',"nome":"Funções do 2º grau","disciplina":{"id":'$D'}}'

echo "== Prova / Questão =="
req 200 GET  /api/provas
req 200 GET  /api/provas/$P
req 200 GET  /api/questoes
req 200 GET  /api/questoes/$Q1
req 200 PUT  /api/questoes/$Q1 '{"id":'$Q1',"numero":1,"enunciado":"Enunciado editado","letraCorreta":"B","prova":{"id":'$P'},"topico":{"id":'$T'}}'
req 400 POST /api/questoes '{"numero":9,"enunciado":"sem alternativas","letraCorreta":"A","prova":{"id":'$P'},"topico":{"id":'$T'}}'

echo "== Alternativa =="
req 200 GET  "/api/alternativas?questaoId=$Q1"
req 201 POST /api/alternativas '{"id":'$Q1',"letra":"E","texto":"opção E"}';        AL=$(id)
req 200 GET  /api/alternativas/$AL
req 200 PUT  /api/alternativas/$AL '{"id":'$Q1',"letra":"E","texto":"opção E editada"}'
req 409 POST /api/alternativas '{"id":'$Q1',"letra":"A","texto":"duplicada"}'
req 400 POST /api/alternativas '{"id":'$Q1',"letra":"Z","texto":"letra inválida"}'

echo "== Simulado + questões do simulado =="
req 201 POST /api/simulados '{"usuarioId":'$U',"nome":"Simulado 1","tempoLimiteMinutos":60}'; SIM=$(id)
req 200 GET  /api/simulados/$SIM
req 200 GET  /api/simulados/usuario/$U
req 200 PUT  /api/simulados/$SIM '{"usuarioId":'$U',"nome":"Simulado 1 editado","tempoLimiteMinutos":90}'
req 201 POST /api/simulados/$SIM/questoes '{"questaoId":'$Q1',"ordem":1}'
req 201 POST /api/simulados/$SIM/questoes '{"questaoId":'$Q2',"ordem":2}'
req 409 POST /api/simulados/$SIM/questoes '{"questaoId":'$Q1',"ordem":3}'
req 409 POST /api/simulados/$SIM/questoes '{"questaoId":'$Q3',"ordem":1}'
req 200 GET  /api/simulados/$SIM/questoes
req 200 PUT  /api/simulados/$SIM/questoes/$Q2 '{"questaoId":'$Q2',"ordem":3}'

echo "== Tentativa / Resposta =="
req 201 POST /api/tentativas '{"usuarioId":'$U',"simuladoId":'$SIM'}';              TE=$(id)
req 200 GET  /api/tentativas/$TE
req 201 POST /api/respostas '{"usuarioId":'$U',"questaoId":'$Q1',"letraMarcada":"B","tentativaId":'$TE'}'; R=$(id)
req 200 GET  /api/respostas/$R
req 200 GET  /api/respostas/usuario/$U
req 200 GET  /api/respostas/tentativa/$TE
req 200 PATCH /api/tentativas/$TE/finalizar
req 409 PATCH /api/tentativas/$TE/finalizar
req 409 POST /api/respostas '{"usuarioId":'$U',"questaoId":'$Q2',"letraMarcada":"A","tentativaId":'$TE'}'
req 200 GET  /api/tentativas/usuario/$U

echo "== Meta / Atividade / Token / Prova salva =="
req 201 POST /api/metas '{"usuarioId":'$U',"areaId":'$A',"taxaDesejada":80,"dataAlvo":"2027-12-01"}'; M=$(id)
req 200 GET  /api/metas/$M
req 200 GET  /api/metas/usuario/$U
req 200 PUT  /api/metas/$M '{"usuarioId":'$U',"areaId":'$A',"taxaDesejada":90,"dataAlvo":"2027-12-01"}'
req 400 POST /api/metas '{"usuarioId":'$U',"areaId":'$A',"taxaDesejada":500}'
req 201 POST /api/atividades '{"usuarioId":'$U',"areaId":'$A',"tipo":"RESPOSTA"}';   AT=$(id)
req 200 GET  /api/atividades/$AT
req 200 GET  /api/atividades/usuario/$U
req 201 POST /api/tokens '{"usuarioId":'$U',"tipo":"VERIFICACAO_EMAIL"}'
COD=$(echo "$BODY" | grep -o '"codigo":"[^"]*"' | head -1 | cut -d'"' -f4)
req 200 GET  /api/tokens/usuario/$U
req 200 PATCH /api/tokens/validar/$COD
req 201 POST /api/provas-salvas/$U/$P
req 200 GET  /api/provas-salvas/usuario/$U

if [ "$LIMPAR" = "1" ]; then
  echo; echo "############ FASE 3: LIMPAR ############"
  req 204 DELETE /api/provas-salvas/$U/$P
  req 204 DELETE /api/respostas/$R
  req 204 DELETE /api/tentativas/$TE
  req 204 DELETE /api/simulados/$SIM/questoes/$Q1
  req 204 DELETE /api/simulados/$SIM/questoes/$Q2
  req 204 DELETE /api/simulados/$SIM
  req 204 DELETE /api/atividades/$AT
  req 204 DELETE /api/metas/$M
  req 204 DELETE /api/alternativas/$AL
  req 204 DELETE /api/questoes/$Q1
  req 204 DELETE /api/questoes/$Q2
  req 204 DELETE /api/questoes/$Q3
  req 204 DELETE /api/provas/$P
  req 204 DELETE /api/topicos/$T
  req 204 DELETE /api/disciplinas/$D
  req 204 DELETE /api/areas/$A
  req 204 DELETE /api/usuarios/$U
else
  echo; echo "(Dados mantidos no banco)"
fi

echo; echo "Resultado: $OK ok, $FAIL com divergência"