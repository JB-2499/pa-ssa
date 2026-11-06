package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.AreaEntity;
import java.util.List;

public interface AreaService {

    List<AreaEntity> listarTodas();

    AreaEntity buscarPorId(Integer id);

    AreaEntity salvar(AreaEntity area);

    AreaEntity atualizar(Integer id, AreaEntity area);

    void excluir(Integer id);
}