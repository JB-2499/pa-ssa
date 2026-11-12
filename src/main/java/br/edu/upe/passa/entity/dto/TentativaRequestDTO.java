package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotNull;

public record TentativaRequestDTO(
        @NotNull Integer usuarioId,
        Integer simuladoId,
        Integer provaId
) {
}
