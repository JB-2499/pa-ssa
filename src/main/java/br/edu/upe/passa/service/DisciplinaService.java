package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Disciplina;
import java.util.List;

public interface DisciplinaService {

    List<Disciplina> listarTodas();

    Disciplina buscarPorId(Integer id);

    Disciplina salvar(Disciplina disciplina);

    Disciplina atualizar(Integer id, Disciplina disciplina);

    void excluir(Integer id);
}