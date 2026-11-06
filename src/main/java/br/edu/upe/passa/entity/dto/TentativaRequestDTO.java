package br.edu.upe.passa.dto;

import jakarta.validation.constraints.NotNull;

public record TentativaRequestDTO(
        @NotNull Integer usuarioId,
        Integer simuladoId,
        Integer provaId
) {
}
