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
public class AlternativaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlternativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao", nullable = false)
    private QuestaoEntity questao;

    @Column(nullable = false, length = 1)
    private String letra;

    @Column(nullable = false, length = 500)
    private String texto;
}