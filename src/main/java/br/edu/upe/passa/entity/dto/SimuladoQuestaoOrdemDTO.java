package br.edu.upe.passa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SimuladoQuestaoOrdemDTO(
        @NotNull @Min(1) Integer ordem
) {
}
