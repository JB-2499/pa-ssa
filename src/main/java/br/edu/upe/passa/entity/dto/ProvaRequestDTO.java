package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Enum.Serie;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProvaRequestDTO(
        @NotNull @Positive Integer ano,
        @NotNull Serie serie,
        @NotNull @Positive Integer tempoMinutos
) {
}
