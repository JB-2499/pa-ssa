package br.edu.upe.passa.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RequestUsuario {

    @NotBlank
    String nome,
    @NotBlank @Email
    String email,
    @NotBlank @Size(min=8)
    String senha
}
