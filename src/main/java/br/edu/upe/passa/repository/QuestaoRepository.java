package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Questao;
import br.edu.upe.passa.entity.Enum.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Integer> {

    List<Questao> findByProva_Ano(Integer ano);

    List<Questao> findByTopico_Id(Integer id);

    List<Questao> findByTopico_Disciplina_Id(Integer id);

    List<Questao> findByProva_Serie(Serie serie);

    List<Questao> findByProva_AnoAndProva_Serie(
            Integer ano, Serie serie
    );
}