package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.ProvaSalva;

import java.time.LocalDateTime;

public record ProvaSalvaResponseDTO(
        Integer usuarioId,
        Integer provaId,
        LocalDateTime salvoEm
) {
    public static ProvaSalvaResponseDTO fromEntity(ProvaSalva provaSalva) {
        return new ProvaSalvaResponseDTO(
                provaSalva.getId().getIdUsuario(),
                provaSalva.getId().getIdProva(),
                provaSalva.getSalvoEm()
        );
    }
}
