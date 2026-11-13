package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Area;

public record AreaResponseDTO(
        Integer id,
        String nome
) {
    public static AreaResponseDTO fromEntity(Area area) {
        return new AreaResponseDTO(
                area.getId(),
                area.getNome()
        );
    }
}
