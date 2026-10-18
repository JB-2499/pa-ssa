package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer id;

    @NotBlank
    @Column(name = "nome_usuario", length = 50, unique = true, nullable = false)
    private String nomeUsuario;

    @NotBlank
    @Email
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Boolean verificado;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String escola;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String serie;
}