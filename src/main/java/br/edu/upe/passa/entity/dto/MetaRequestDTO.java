package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MetaRequestDTO(
        @NotNull Integer usuarioId,
        @NotNull Integer areaId,
        @NotNull @Min(1) @Max(100) Integer taxaDesejada,
        LocalDate dataAlvo
) {
}
