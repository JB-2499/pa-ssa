package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import br.edu.upe.passa.entity.Enum.Serie;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prova")
@Getter
@Setter
@NoArgsConstructor

public class ProvaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProva;

    @Column(nullable = false)
    private Integer ano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Serie serie;

    @Column(nullable = false)
    private Integer tempoMinutos;

    @OneToMany(mappedBy = "prova")
    private List<QuestaoEntity> questoes = new ArrayList<>();

    @OneToMany(mappedBy = "prova")
    private List<ProvaSalvaEntity> provasSalvas = new ArrayList<>();
}