package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.ProvaEntity;
import br.edu.upe.passa.entity.Enum.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<ProvaEntity, Integer> {

    List<ProvaEntity> findByAno(Integer ano);

    List<ProvaEntity> findBySerie(Serie serie);

    List<ProvaEntity> findByAnoAndSerie(Integer ano, Serie serie);
}