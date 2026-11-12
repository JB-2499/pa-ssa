package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SimuladoQuestaoRequestDTO(
        @NotNull Integer questaoId,
        @NotNull @Min(1) Integer ordem
) {
}
