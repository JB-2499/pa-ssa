package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AtividadeRequestDTO(
        @NotNull Integer usuarioId,
        Integer areaId,
        @NotBlank @Size(max = 30) String tipo,
        LocalDateTime ocorridaEm
) {
}
