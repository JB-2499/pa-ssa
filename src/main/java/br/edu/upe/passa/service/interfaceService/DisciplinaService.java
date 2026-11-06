package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.DisciplinaEntity;
import java.util.List;

public interface DisciplinaService {

    List<DisciplinaEntity> listarTodas();
    DisciplinaEntity buscarPorId(Long id);
    DisciplinaEntity salvar(DisciplinaEntity disciplina);
    DisciplinaEntity atualizar(Long id, DisciplinaEntity disciplina);
    void excluir(Long id);
}