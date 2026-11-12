package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Tentativa;

import java.util.List;

public interface TentativaService {

    Tentativa iniciar(Integer usuarioId, Integer simuladoId, Integer provaId);

    Tentativa buscarPorId(Integer id);

    List<Tentativa> listarPorUsuario(Integer usuarioId);

    Tentativa finalizar(Integer id);

    void deletar(Integer id);
}
