package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.entity.Enum.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Integer> {

    List<Prova> findByAno(Integer ano);

    List<Prova> findBySerie(Serie serie);

    List<Prova> findByAnoAndSerie(Integer ano, Serie serie);
}