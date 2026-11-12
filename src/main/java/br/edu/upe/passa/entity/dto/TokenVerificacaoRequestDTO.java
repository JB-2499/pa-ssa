package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TokenVerificacaoRequestDTO(
        @NotNull Integer usuarioId,
        @NotBlank String tipo,
        String emailNovo
) {
}
