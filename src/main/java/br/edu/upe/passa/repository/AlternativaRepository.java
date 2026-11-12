package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Alternativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativaRepository extends JpaRepository<Alternativa, Integer> {

    List<Alternativa> findByQuestao_IdQuestao(Integer idQuestao);
}