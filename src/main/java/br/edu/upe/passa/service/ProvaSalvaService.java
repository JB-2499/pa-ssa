package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.ProvaSalva;
import java.util.List;

public interface ProvaSalvaService {

    ProvaSalva salvar(Integer idUsuario, Integer idProva);

    List<ProvaSalva> listarPorUsuario(Integer idUsuario);

    void remover(Integer idUsuario, Integer idProva);
}