package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AlternativaRequestDTO(
        @NotNull @Positive Integer id,
        @NotBlank @Pattern(regexp = "[A-E]", message = "A letra deve ser de A a E") String letra,
        @NotBlank @Size(max = 500) String texto
) {
}