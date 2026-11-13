package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Questao;

import java.util.List;

public record QuestaoResponseDTO(
        Integer id,
        Integer numero,
        String enunciado,
        String letraCorreta,
        String imagemUrl,
        Integer provaId,
        Integer topicoId,
        List<AlternativaResponseDTO> alternativas
) {
    public static QuestaoResponseDTO fromEntity(Questao questao) {
        return new QuestaoResponseDTO(
                questao.getId(),
                questao.getNumero(),
                questao.getEnunciado(),
                questao.getLetraCorreta(),
                questao.getImagemUrl(),
                questao.getProva().getId(),
                questao.getTopico().getId(),
                questao.getAlternativas().stream()
                        .map(AlternativaResponseDTO::fromEntity)
                        .toList()
        );
    }
}
