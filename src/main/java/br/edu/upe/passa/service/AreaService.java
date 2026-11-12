package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Area;
import java.util.List;

public interface AreaService {

    List<Area> listarTodas();

    Area buscarPorId(Integer id);

    Area salvar(Area area);

    Area atualizar(Integer id, Area area);

    void excluir(Integer id);
}