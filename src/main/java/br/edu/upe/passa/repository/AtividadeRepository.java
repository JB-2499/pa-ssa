package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

    List<Atividade> findByUsuarioIdOrderByOcorridaEmDesc(Integer usuarioId);
}
