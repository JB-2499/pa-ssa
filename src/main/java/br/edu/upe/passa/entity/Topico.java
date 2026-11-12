package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topico")
    private Integer id;

    @Column(nullable = false, length = 80)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_disciplina", nullable = false)
    private Disciplina disciplina;

    @OneToMany(mappedBy = "topico")
    private List<Questao> questoes = new ArrayList<>();
}