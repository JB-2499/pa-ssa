package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DisciplinaRequestDTO(
        @NotBlank @Size(max = 255) String nome,
        @NotNull Integer areaId
) {
}
