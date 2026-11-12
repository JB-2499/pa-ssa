package br.edu.upe.passa.entity.dto;

import br.edu.upe.passa.entity.Usuario;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Integer id,
        String nomeUsuario,
        String email,
        Boolean verificado,
        LocalDate dataNascimento,
        String escola,
        String serie
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeUsuario(),
                usuario.getEmail(),
                usuario.getVerificado(),
                usuario.getDataNascimento(),
                usuario.getEscola(),
                usuario.getSerie()
        );
    }
}