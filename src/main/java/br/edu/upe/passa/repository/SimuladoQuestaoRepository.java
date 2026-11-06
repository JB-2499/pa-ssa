package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.SimuladoQuestao;
import br.edu.upe.passa.entity.SimuladoQuestaoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimuladoQuestaoRepository extends JpaRepository<SimuladoQuestao, SimuladoQuestaoId> {

    List<SimuladoQuestao> findBySimuladoIdOrderByOrdemAsc(Integer simuladoId);

    boolean existsBySimuladoIdAndOrdem(Integer simuladoId, Integer ordem);
}
