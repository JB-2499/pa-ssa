package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Atividade;

import java.time.LocalDateTime;

public record AtividadeResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer areaId,
        String tipo,
        LocalDateTime ocorridaEm
) {
    public static AtividadeResponseDTO fromEntity(Atividade atividade) {
        return new AtividadeResponseDTO(
                atividade.getId(),
                atividade.getUsuario().getId(),
                atividade.getArea() != null ? atividade.getArea().getId() : null,
                atividade.getTipo(),
                atividade.getOcorridaEm()
        );
    }
}
