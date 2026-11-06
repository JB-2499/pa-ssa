package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.TopicoEntity;
import java.util.List;

public interface TopicoService {

    List<TopicoEntity> listarTodos();

    TopicoEntity buscarPorId(Long id);

    TopicoEntity salvar(TopicoEntity topico);

    TopicoEntity atualizar(Long id, TopicoEntity topico);

    void excluir(Long id);
}