package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Tentativa;

import java.time.LocalDateTime;

public record TentativaResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer simuladoId,
        Integer provaId,
        LocalDateTime iniciadaEm,
        LocalDateTime finalizadaEm
) {
    public static TentativaResponseDTO fromEntity(Tentativa tentativa) {
        return new TentativaResponseDTO(
                tentativa.getId(),
                tentativa.getUsuario().getId(),
                tentativa.getSimulado() != null ? tentativa.getSimulado().getId() : null,
                tentativa.getProva() != null ? tentativa.getProva().getId() : null,
                tentativa.getIniciadaEm(),
                tentativa.getFinalizadaEm()
        );
    }
}
