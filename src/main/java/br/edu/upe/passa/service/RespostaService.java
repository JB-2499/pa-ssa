package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Resposta;

import java.util.List;

public interface RespostaService {

    Resposta registrar(Integer usuarioId, Integer questaoId, String letraMarcada, Integer tentativaId);

    Resposta buscarPorId(Integer id);

    List<Resposta> listarPorUsuario(Integer usuarioId);

    List<Resposta> listarPorTentativa(Integer tentativaId);

    void deletar(Integer id);
}
