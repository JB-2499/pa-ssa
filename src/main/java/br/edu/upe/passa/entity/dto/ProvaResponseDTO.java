package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Enum.Serie;
import br.edu.upe.passa.entity.Prova;

public record ProvaResponseDTO(
        Integer id,
        Integer ano,
        Serie serie,
        Integer tempoMinutos
) {
    public static ProvaResponseDTO fromEntity(Prova prova) {
        return new ProvaResponseDTO(
                prova.getId(),
                prova.getAno(),
                prova.getSerie(),
                prova.getTempoMinutos()
        );
    }
}
