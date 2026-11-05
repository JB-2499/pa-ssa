package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "token_verificacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenVerificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String tipo;

    @NotBlank
    @Column(length = 255, unique = true, nullable = false)
    private String codigo;

    @Column(name = "email_novo", length = 120)
    private String emailNovo;

    @NotNull
    @Column(name = "expira_em", nullable = false)
    private LocalDateTime expiraEm;

    @Column(name = "usado_em")
    private LocalDateTime usadoEm;
}
