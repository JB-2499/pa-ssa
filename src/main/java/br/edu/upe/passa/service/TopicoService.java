package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Topico;
import java.util.List;

public interface TopicoService {

    List<Topico> listarTodos();

    Topico buscarPorId(Integer id);

    Topico salvar(Topico topico);

    Topico atualizar(Integer id, Topico topico);

    void excluir(Integer id);
}