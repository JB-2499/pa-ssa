package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.AlternativaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativaRepository extends JpaRepository<AlternativaEntity, Long> {

    List<AlternativaEntity> findByQuestao_IdQuestao(Long idQuestao);
}