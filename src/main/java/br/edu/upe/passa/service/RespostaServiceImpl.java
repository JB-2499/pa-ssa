package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.QuestaoEntity;
import br.edu.upe.passa.entity.Resposta;
import br.edu.upe.passa.entity.Tentativa;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.QuestaoRepository;
import br.edu.upe.passa.repository.RespostaRepository;
import br.edu.upe.passa.repository.TentativaRepository;
import br.edu.upe.passa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RespostaServiceImpl implements RespostaService {

    private static final List<String> LETRAS_VALIDAS = List.of("A", "B", "C", "D", "E");

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final QuestaoRepository questaoRepository;
    private final TentativaRepository tentativaRepository;

    @Override
    @Transactional
    public Resposta registrar(Integer usuarioId, Integer questaoId, String letraMarcada, Integer tentativaId) {
        String letra = (letraMarcada == null) ? "" : letraMarcada.trim().toUpperCase();
        if (!LETRAS_VALIDAS.contains(letra)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Letra inválida. Valores aceitos: " + LETRAS_VALIDAS);
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        QuestaoEntity questao = questaoRepository.findById(questaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questão não encontrada"));

        Tentativa tentativa = null;
        if (tentativaId != null) {
            tentativa = tentativaRepository.findById(tentativaId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tentativa não encontrada"));

            if (!tentativa.getUsuario().getId().equals(usuarioId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Esta tentativa pertence a outro usuário");
            }
            if (tentativa.getFinalizadaEm() != null) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta tentativa já foi finalizada");
            }
            if (respostaRepository.existsByTentativaIdAndQuestaoId(tentativaId, questaoId)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Esta questão já foi respondida nesta tentativa");
            }
        }

        Resposta resposta = new Resposta();
        resposta.setUsuario(usuario);
        resposta.setQuestao(questao);
        resposta.setTentativa(tentativa);
        resposta.setLetraMarcada(letra);

        return respostaRepository.save(resposta);
    }

    @Override
    @Transactional(readOnly = true)
    public Resposta buscarPorId(Integer id) {
        return respostaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resposta não encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resposta> listarPorUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return respostaRepository.findByUsuarioIdOrderByRespondidaEmDesc(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resposta> listarPorTentativa(Integer tentativaId) {
        if (!tentativaRepository.existsById(tentativaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tentativa não encontrada");
        }
        return respostaRepository.findByTentativaId(tentativaId);
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
        Resposta resposta = buscarPorId(id);
        respostaRepository.delete(resposta);
    }
}
