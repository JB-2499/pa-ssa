package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Alternativa;

public record AlternativaResponseDTO(
        Integer id,
        Integer questaoId,
        String letra,
        String texto
) {
    public static AlternativaResponseDTO fromEntity(Alternativa alternativa) {
        return new AlternativaResponseDTO(
                alternativa.getId(),
                alternativa.getQuestao().getId(),
                alternativa.getLetra(),
                alternativa.getTexto()
        );
    }
}