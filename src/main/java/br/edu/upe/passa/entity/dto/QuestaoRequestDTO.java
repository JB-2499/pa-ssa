package br.edu.upe.passa.entity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record QuestaoRequestDTO(
        @NotNull @Positive Integer numero,
        @NotBlank String enunciado,
        @NotBlank @Pattern(regexp = "[A-E]", message = "A letra correta deve ser de A a E") String letraCorreta,
        @Size(max = 255) String imagemUrl,
        @NotNull Integer provaId,
        @NotNull Integer topicoId,
        @NotEmpty @Valid List<QuestaoAlternativaRequestDTO> alternativas
) {
}
