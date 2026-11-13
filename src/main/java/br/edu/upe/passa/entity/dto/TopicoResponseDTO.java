package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Topico;

public record TopicoResponseDTO(
        Integer id,
        String nome,
        Integer disciplinaId
) {
    public static TopicoResponseDTO fromEntity(Topico topico) {
        return new TopicoResponseDTO(
                topico.getId(),
                topico.getNome(),
                topico.getDisciplina().getId()
        );
    }
}
