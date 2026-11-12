package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    List<Disciplina> findByArea_IdArea(Integer idArea);
}