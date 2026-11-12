package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Integer> {

    List<Resposta> findByIdOrderByRespondidaEmDesc(Integer id);

    List<Resposta> findByTentativaId(Integer tentativaId);

    boolean existsByTentativaIdAndQuestao_Id(Integer tentativaId, Integer questaoId);
}
