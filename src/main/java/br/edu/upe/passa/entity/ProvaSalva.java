package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prova_salva")
@Getter
@Setter
@NoArgsConstructor
public class ProvaSalva {

    @EmbeddedId
    private ProvaSalvaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProva")
    @JoinColumn(name = "id_prova")
    private Prova prova;

    @Column(nullable = false)
    private LocalDateTime salvoEm;
}