package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.QuestaoEntity;
import br.edu.upe.passa.entity.Enum.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<QuestaoEntity, Integer> {

    List<QuestaoEntity> findByProva_Ano(Integer ano);

    List<QuestaoEntity> findByTopico_IdTopico(Integer idTopico);

    List<QuestaoEntity> findByTopico_Disciplina_IdDisciplina(Integer idDisciplina);

    List<QuestaoEntity> findByProva_Serie(Serie serie);

    List<QuestaoEntity> findByProva_AnoAndProva_Serie(
            Integer ano, Serie serie
    );
}