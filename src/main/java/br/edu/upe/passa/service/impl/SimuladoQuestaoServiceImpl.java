package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Questao;
import br.edu.upe.passa.entity.Simulado;
import br.edu.upe.passa.entity.SimuladoQuestao;
import br.edu.upe.passa.entity.SimuladoQuestaoId;
import br.edu.upe.passa.repository.QuestaoRepository;
import br.edu.upe.passa.repository.SimuladoQuestaoRepository;
import br.edu.upe.passa.repository.SimuladoRepository;
import br.edu.upe.passa.service.SimuladoQuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimuladoQuestaoServiceImpl implements SimuladoQuestaoService {

    private final SimuladoQuestaoRepository simuladoQuestaoRepository;
    private final SimuladoRepository simuladoRepository;
    private final QuestaoRepository questaoRepository;

    @Override
    @Transactional
    public SimuladoQuestao adicionar(Integer simuladoId, Integer questaoId, Integer ordem) {
        validarOrdem(ordem);

        Simulado simulado = simuladoRepository.findById(simuladoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulado não encontrado"));
        Questao questao = questaoRepository.findById(questaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questão não encontrada"));

        SimuladoQuestaoId id = new SimuladoQuestaoId(simuladoId, questaoId);

        if (simuladoQuestaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta questão já está no simulado");
        }
        if (simuladoQuestaoRepository.existsBySimuladoIdAndOrdem(simuladoId, ordem)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma questão nessa posição do simulado");
        }

        SimuladoQuestao simuladoQuestao = new SimuladoQuestao();
        simuladoQuestao.setId(id);
        simuladoQuestao.setSimulado(simulado);
        simuladoQuestao.setQuestao(questao);
        simuladoQuestao.setOrdem(ordem);

        return simuladoQuestaoRepository.save(simuladoQuestao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SimuladoQuestao> listarPorSimulado(Integer simuladoId) {
        if (!simuladoRepository.existsById(simuladoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulado não encontrado");
        }
        return simuladoQuestaoRepository.findBySimuladoIdOrderByOrdemAsc(simuladoId);
    }

    @Override
    @Transactional
    public SimuladoQuestao atualizarOrdem(Integer simuladoId, Integer questaoId, Integer ordem) {
        validarOrdem(ordem);

        SimuladoQuestao simuladoQuestao = buscar(simuladoId, questaoId);

        if (!simuladoQuestao.getOrdem().equals(ordem)
                && simuladoQuestaoRepository.existsBySimuladoIdAndOrdem(simuladoId, ordem)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma questão nessa posição do simulado");
        }

        simuladoQuestao.setOrdem(ordem);
        return simuladoQuestaoRepository.save(simuladoQuestao);
    }

    @Override
    @Transactional
    public void remover(Integer simuladoId, Integer questaoId) {
        simuladoQuestaoRepository.delete(buscar(simuladoId, questaoId));
    }

    private SimuladoQuestao buscar(Integer simuladoId, Integer questaoId) {
        return simuladoQuestaoRepository.findById(new SimuladoQuestaoId(simuladoId, questaoId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Esta questão não faz parte do simulado"));
    }

    private void validarOrdem(Integer ordem) {
        if (ordem == null || ordem < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A ordem deve ser maior ou igual a 1");
        }
    }
}
