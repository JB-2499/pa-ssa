package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    Optional<Area> findByNome(String nome);

    boolean existsByNome(String nome);
}