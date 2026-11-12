package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Prova;
import java.util.List;

public interface ProvaService {

    List<Prova> listarTodas();

    Prova buscarPorId(Integer id);

    Prova salvar(Prova prova);

    void excluir(Integer id);
}