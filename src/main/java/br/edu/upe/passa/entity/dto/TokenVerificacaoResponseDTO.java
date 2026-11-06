package br.edu.upe.passa.dto;

import br.edu.upe.passa.entity.TokenVerificacao;

import java.time.LocalDateTime;

public record TokenVerificacaoResponseDTO(
        Integer id,
        Integer usuarioId,
        String tipo,
        String codigo,
        String emailNovo,
        LocalDateTime expiraEm,
        LocalDateTime usadoEm
) {
    public static TokenVerificacaoResponseDTO fromEntity(TokenVerificacao token) {
        return new TokenVerificacaoResponseDTO(
                token.getId(),
                token.getUsuario().getId(),
                token.getTipo(),
                token.getCodigo(),
                token.getEmailNovo(),
                token.getExpiraEm(),
                token.getUsadoEm()
        );
    }
}
