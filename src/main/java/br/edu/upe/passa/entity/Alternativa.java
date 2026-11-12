package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alternativa", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_questao", "letra"})})
@Getter
@Setter
@NoArgsConstructor
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alternativa")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao", nullable = false)
    private Questao questao;

    @Column(nullable = false, length = 1)
    private String letra;

    @Column(nullable = false, length = 500)
    private String texto;
}