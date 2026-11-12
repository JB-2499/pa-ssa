package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Simulado;

import java.util.List;

public interface SimuladoService {

    Simulado criar(Integer usuarioId, String nome, Integer tempoLimiteMinutos);

    Simulado buscarPorId(Integer id);

    List<Simulado> listarPorUsuario(Integer usuarioId);

    Simulado atualizar(Integer id, String nome, Integer tempoLimiteMinutos);

    void deletar(Integer id);
}
