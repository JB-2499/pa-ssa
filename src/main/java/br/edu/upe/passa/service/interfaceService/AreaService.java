package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.AreaEntity;
import java.util.List;

public interface AreaService {

    List<AreaEntity> listarTodas();

    AreaEntity buscarPorId(Long id);

    AreaEntity salvar(AreaEntity area);

    AreaEntity atualizar(Long id, AreaEntity area);

    void excluir(Long id);
}