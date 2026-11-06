package br.edu.upe.passa.dto;

import java.time.LocalDateTime;

public record RespostaResponseDTO(

        Long idResposta,

        Long idQuestao,

        String letraMarcada,

        Boolean correta,

        LocalDateTime respondidaEm

) {}