package br.edu.upe.passa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SimuladoRequestDTO(
        @NotNull Integer usuarioId,
        @NotBlank @Size(max = 150) String nome,
        Integer tempoLimiteMinutos
) {
}
