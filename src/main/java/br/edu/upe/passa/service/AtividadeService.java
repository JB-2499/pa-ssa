package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Atividade;

import java.time.LocalDateTime;
import java.util.List;

public interface AtividadeService {

    Atividade registrar(Integer usuarioId, Integer areaId, String tipo, LocalDateTime ocorridaEm);

    Atividade buscarPorId(Integer id);

    List<Atividade> listarPorUsuario(Integer usuarioId);

    void deletar(Integer id);
}
