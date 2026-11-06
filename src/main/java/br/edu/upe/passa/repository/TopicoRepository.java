package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {

    List<TopicoEntity> findByDisciplina_IdDisciplina(Long idDisciplina);
}