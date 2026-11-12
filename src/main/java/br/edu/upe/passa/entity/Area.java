package br.edu.upe.passa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "area")
@Getter
@Setter
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "area")
    private List<Disciplina> disciplinas = new ArrayList<>();
}