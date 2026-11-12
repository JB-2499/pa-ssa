package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.dto.AlternativaRequestDTO;
import br.edu.upe.passa.entity.Alternativa;

import java.util.List;

public interface AlternativaService {

    List<Alternativa> listarTodas();

    List<Alternativa> listarPorQuestao(Integer questaoId);

    Alternativa buscarPorId(Integer id);

    Alternativa criar(AlternativaRequestDTO dto);

    Alternativa atualizar(Integer id, AlternativaRequestDTO dto);

    void excluir(Integer id);
}