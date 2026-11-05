package br.edu.upe.passa.entity;

import br.edu.upe.passa.entity.Enum.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String nome;
    @Column(unique = true, nullable = true)
    private String email;
    private String telefone;
    private String senha;
    @Enumerated(EnumType.STRING)

    private Perfil perfil;

}
