package br.edu.upe.passa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SimuladoQuestaoId implements Serializable {

    @Column(name = "id_simulado")
    private Integer simuladoId;

    @Column(name = "id_questao")
    private Integer questaoId;
}
