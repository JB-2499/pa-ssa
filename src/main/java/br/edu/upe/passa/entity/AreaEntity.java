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
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArea;
    @Column(nullable = false, unique = true)
    private String nome;
    @OneToMany(mappedBy = "area")
    private List<DisciplinaEntity> disciplinas = new ArrayList<>();
}