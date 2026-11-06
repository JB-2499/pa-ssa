package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao", nullable = false)
    private Questao questao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tentativa")
    private Tentativa tentativa;

    @NotBlank
    @Pattern(regexp = "[A-E]")
    @Column(name = "letra_marcada", length = 1, nullable = false)
    private String letraMarcada;

    @NotNull
    @Column(name = "respondida_em", nullable = false)
    private LocalDateTime respondidaEm;

    @PrePersist
    protected void aoPersistir() {
        this.respondidaEm = LocalDateTime.now();
    }
}
