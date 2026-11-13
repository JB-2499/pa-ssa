package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AreaRequestDTO(
        @NotNull
        @NotBlank @Size(max = 255) String nome
) {
}
