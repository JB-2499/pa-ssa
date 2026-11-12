package br.edu.upe.passa.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record UsuarioRequestDTO(
        @NotBlank String nomeUsuario,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull @Past LocalDate dataNascimento,
        @NotBlank String escola,
        @NotBlank String serie
) {
}