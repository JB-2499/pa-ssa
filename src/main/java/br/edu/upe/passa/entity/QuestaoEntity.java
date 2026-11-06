package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questao")
@Getter
@Setter
@NoArgsConstructor
public class QuestaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuestao;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @Column(nullable = false, length = 1)
    private String letraCorreta;

    @Column(length = 255)
    private String imagemUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prova", nullable = false)
    private ProvaEntity prova;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico", nullable = false)
    private TopicoEntity topico;

    @OneToMany(
            mappedBy = "questao",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AlternativaEntity> alternativas = new ArrayList<>();
}