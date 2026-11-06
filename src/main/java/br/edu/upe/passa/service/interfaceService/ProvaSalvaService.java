package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.ProvaSalvaEntity;
import java.util.List;

public interface ProvaSalvaService {

    ProvaSalvaEntity salvar(Long idUsuario, Long idProva);

    List<ProvaSalvaEntity> listarPorUsuario(Long idUsuario);

    void remover(Long idUsuario, Long idProva);
}