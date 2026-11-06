package br.edu.upe.passa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RespostaRequestDTO(
        @NotNull Integer usuarioId,
        @NotNull Integer questaoId,
        @NotBlank @Size(min = 1, max = 1) String letraMarcada,
        Integer tentativaId
) {
}
