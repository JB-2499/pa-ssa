package br.edu.upe.passa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "meta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    @NotNull
    @Min(1)
    @Max(100)
    @Column(name = "taxa_desejada", nullable = false)
    private Integer taxaDesejada;

    @Column(name = "data_alvo")
    private LocalDate dataAlvo;
}
