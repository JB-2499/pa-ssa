package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TopicoRequestDTO(
        @NotBlank @Size(max = 80) String nome,
        @NotNull Integer disciplinaId
) {
}
