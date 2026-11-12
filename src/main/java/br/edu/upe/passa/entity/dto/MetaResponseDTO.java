package br.edu.upe.passa.dto;

import br.edu.upe.passa.entity.Meta;

import java.time.LocalDate;

public record MetaResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer areaId,
        Integer taxaDesejada,
        LocalDate dataAlvo
) {
    public static MetaResponseDTO fromEntity(Meta meta) {
        return new MetaResponseDTO(
                meta.getId(),
                meta.getUsuario().getId(),
                meta.getArea().getId(),
                meta.getTaxaDesejada(),
                meta.getDataAlvo()
        );
    }
}
