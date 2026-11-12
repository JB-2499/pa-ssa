package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Questao;
import java.util.List;

public interface QuestaoService {

    List<Questao> listarTodas();

    Questao buscarPorId(Integer id);

    Questao salvar(Questao questao);

    Questao atualizar(Integer id, Questao questao);

    void excluir(Integer id);
}