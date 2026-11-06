package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "simulado_questao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimuladoQuestao {

    @EmbeddedId
    private SimuladoQuestaoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("simuladoId")
    @JoinColumn(name = "id_simulado")
    private Simulado simulado;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("questaoId")
    @JoinColumn(name = "id_questao")
    private Questao questao;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer ordem;
}
