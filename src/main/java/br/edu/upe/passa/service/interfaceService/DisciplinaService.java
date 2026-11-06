package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.DisciplinaEntity;
import java.util.List;

public interface DisciplinaService {

    List<DisciplinaEntity> listarTodas();

    DisciplinaEntity buscarPorId(Integer id);

    DisciplinaEntity salvar(DisciplinaEntity disciplina);

    DisciplinaEntity atualizar(Integer id, DisciplinaEntity disciplina);

    void excluir(Integer id);
}