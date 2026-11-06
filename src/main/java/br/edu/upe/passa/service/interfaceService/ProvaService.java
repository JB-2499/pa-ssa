package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.ProvaEntity;
import java.util.List;

public interface ProvaService {

    List<ProvaEntity> listarTodas();
    ProvaEntity buscarPorId(Long id);
    ProvaEntity salvar(ProvaEntity prova);
    void excluir(Long id);
}