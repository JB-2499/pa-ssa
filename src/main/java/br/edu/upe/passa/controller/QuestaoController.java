package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Alternativa;
import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.entity.Questao;
import br.edu.upe.passa.entity.Topico;
import br.edu.upe.passa.entity.dto.QuestaoRequestDTO;
import br.edu.upe.passa.entity.dto.QuestaoResponseDTO;
import br.edu.upe.passa.service.QuestaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/questoes")
@RequiredArgsConstructor
public class QuestaoController {

    private final QuestaoService questaoService;

    @GetMapping
    public ResponseEntity<List<QuestaoResponseDTO>> listarTodas() {
        List<QuestaoResponseDTO> questoes = questaoService.listarTodas().stream()
                .map(QuestaoResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(questoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestaoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                QuestaoResponseDTO.fromEntity(questaoService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<QuestaoResponseDTO> salvar(@Valid @RequestBody QuestaoRequestDTO dto) {
        Questao salva = questaoService.salvar(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(QuestaoResponseDTO.fromEntity(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestaoResponseDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody QuestaoRequestDTO dto) {

        Questao atualizada = questaoService.atualizar(id, toEntity(dto));
        return ResponseEntity.ok(QuestaoResponseDTO.fromEntity(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        questaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private Questao toEntity(QuestaoRequestDTO dto) {
        Prova prova = new Prova();
        prova.setId(dto.provaId());

        Topico topico = new Topico();
        topico.setId(dto.topicoId());

        Questao questao = new Questao();
        questao.setNumero(dto.numero());
        questao.setEnunciado(dto.enunciado());
        questao.setLetraCorreta(dto.letraCorreta());
        questao.setImagemUrl(dto.imagemUrl());
        questao.setProva(prova);
        questao.setTopico(topico);

        List<Alternativa> alternativas = new ArrayList<>(
                dto.alternativas().stream()
                        .map(altDto -> {
                            Alternativa alternativa = new Alternativa();
                            alternativa.setLetra(altDto.letra());
                            alternativa.setTexto(altDto.texto());
                            alternativa.setQuestao(questao);
                            return alternativa;
                        })
                        .toList()
        );
        questao.setAlternativas(alternativas);

        return questao;
    }
}