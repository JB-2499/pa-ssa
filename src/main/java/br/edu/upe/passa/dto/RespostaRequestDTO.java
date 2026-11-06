package br.edu.upe.passa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RespostaRequestDTO(

        @NotNull
        Long idTentativa,

        @NotNull
        Long idQuestao,

        @NotBlank
        @Pattern(regexp = "[A-Ea-e]")
        String letraMarcada


) {}