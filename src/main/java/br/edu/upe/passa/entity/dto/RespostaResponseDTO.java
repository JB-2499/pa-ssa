package br.edu.upe.passa.dto;

import br.edu.upe.passa.entity.Resposta;

import java.time.LocalDateTime;

public record RespostaResponseDTO(
        Integer id,
        Integer usuarioId,
        Integer questaoId,
        Integer tentativaId,
        String letraMarcada,
        LocalDateTime respondidaEm
) {
    public static RespostaResponseDTO fromEntity(Resposta resposta) {
        return new RespostaResponseDTO(
                resposta.getId(),
                resposta.getUsuario().getId(),
                resposta.getQuestao().getId(),
                resposta.getTentativa() != null ? resposta.getTentativa().getId() : null,
                resposta.getLetraMarcada(),
                resposta.getRespondidaEm()
        );
    }
}
