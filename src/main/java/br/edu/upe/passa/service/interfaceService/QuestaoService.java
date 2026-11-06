package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.QuestaoEntity;
import java.util.List;

public interface QuestaoService {

    List<QuestaoEntity> listarTodas();

    QuestaoEntity buscarPorId(Integer id);

    QuestaoEntity salvar(QuestaoEntity questao);

    QuestaoEntity atualizar(Integer id, QuestaoEntity questao);

    void excluir(Integer id);
}