package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Simulado;

import java.time.LocalDateTime;

public record SimuladoResponseDTO(
        Integer id,
        Integer usuarioId,
        String nome,
        LocalDateTime criadoEm,
        Integer tempoLimiteMinutos
) {
    public static SimuladoResponseDTO fromEntity(Simulado simulado) {
        return new SimuladoResponseDTO(
                simulado.getId(),
                simulado.getUsuario().getId(),
                simulado.getNome(),
                simulado.getCriadoEm(),
                simulado.getTempoLimiteMinutos()
        );
    }
}
