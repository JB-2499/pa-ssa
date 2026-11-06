package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Simulado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimuladoRepository extends JpaRepository<Simulado, Integer> {

    List<Simulado> findByUsuarioId(Integer usuarioId);
}
