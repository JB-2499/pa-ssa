package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.TopicoEntity;
import java.util.List;

public interface TopicoService {

    List<TopicoEntity> listarTodos();

    TopicoEntity buscarPorId(Integer id);

    TopicoEntity salvar(TopicoEntity topico);

    TopicoEntity atualizar(Integer id, TopicoEntity topico);

    void excluir(Integer id);
}