package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tentativa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tentativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tentativa")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_simulado")
    private Simulado simulado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prova")
    private Prova prova;

    @NotNull
    @Column(name = "iniciada_em", nullable = false)
    private LocalDateTime iniciadaEm;

    @Column(name = "finalizada_em")
    private LocalDateTime finalizadaEm;

    @PrePersist
    protected void aoPersistir() {
        this.iniciadaEm = LocalDateTime.now();
    }
}
