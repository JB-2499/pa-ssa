package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.ProvaEntity;
import java.util.List;

public interface ProvaService {

    List<ProvaEntity> listarTodas();

    ProvaEntity buscarPorId(Integer id);

    ProvaEntity salvar(ProvaEntity prova);

    void excluir(Integer id);
}