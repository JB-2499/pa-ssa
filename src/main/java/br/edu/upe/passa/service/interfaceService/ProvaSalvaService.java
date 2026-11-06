package br.edu.upe.passa.service.interfaceService;

import br.edu.upe.passa.entity.ProvaSalvaEntity;
import java.util.List;

public interface ProvaSalvaService {

    ProvaSalvaEntity salvar(Integer idUsuario, Integer idProva);

    List<ProvaSalvaEntity> listarPorUsuario(Integer idUsuario);

    void remover(Integer idUsuario, Integer idProva);
}