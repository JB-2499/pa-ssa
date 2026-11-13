package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SimuladoQuestaoRequestDTO(
        @Positive
        @NotNull Integer questaoId,
        @NotNull @Min(1) Integer ordem
) {
}
