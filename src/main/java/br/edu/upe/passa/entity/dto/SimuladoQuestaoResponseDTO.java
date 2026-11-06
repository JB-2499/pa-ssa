package br.edu.upe.passa.dto;

import br.edu.upe.passa.entity.SimuladoQuestao;

public record SimuladoQuestaoResponseDTO(
        Integer simuladoId,
        Integer questaoId,
        Integer ordem
) {
    public static SimuladoQuestaoResponseDTO fromEntity(SimuladoQuestao sq) {
        return new SimuladoQuestaoResponseDTO(
                sq.getId().getSimuladoId(),
                sq.getId().getQuestaoId(),
                sq.getOrdem()
        );
    }
}
