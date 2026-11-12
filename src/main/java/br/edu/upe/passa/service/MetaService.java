package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Meta;

import java.time.LocalDate;
import java.util.List;

public interface MetaService {

    Meta criar(Integer usuarioId, Integer areaId, Integer taxaDesejada, LocalDate dataAlvo);

    Meta buscarPorId(Integer id);

    List<Meta> listarPorUsuario(Integer usuarioId);

    Meta atualizar(Integer id, Integer taxaDesejada, LocalDate dataAlvo);

    void deletar(Integer id);
}
