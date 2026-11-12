package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.SimuladoQuestao;

import java.util.List;

public interface SimuladoQuestaoService {

    SimuladoQuestao adicionar(Integer simuladoId, Integer questaoId, Integer ordem);

    List<SimuladoQuestao> listarPorSimulado(Integer simuladoId);

    SimuladoQuestao atualizarOrdem(Integer simuladoId, Integer questaoId, Integer ordem);

    void remover(Integer simuladoId, Integer questaoId);
}
