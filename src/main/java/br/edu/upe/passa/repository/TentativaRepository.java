package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Tentativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TentativaRepository extends JpaRepository<Tentativa, Integer> {

    List<Tentativa> findByUsuarioIdOrderByIniciadaEmDesc(Integer usuarioId);
}
