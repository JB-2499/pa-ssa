package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Disciplina;

public record DisciplinaResponseDTO(
        Integer id,
        String nome,
        Integer areaId
) {
    public static DisciplinaResponseDTO fromEntity(Disciplina disciplina) {
        return new DisciplinaResponseDTO(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getArea().getId()
        );
    }
}
