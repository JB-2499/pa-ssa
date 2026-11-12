package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaRepository extends JpaRepository<Meta, Integer> {

    List<Meta> findByUsuarioId(Integer usuarioId);

    boolean existsByUsuarioIdAndArea_IdArea(Integer usuarioId, Integer areaId);
}