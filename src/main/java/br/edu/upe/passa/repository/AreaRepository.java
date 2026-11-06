package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.AreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Long> {

    Optional<AreaEntity> findByNome(String nome);

    boolean existsByNome(String nome);
}