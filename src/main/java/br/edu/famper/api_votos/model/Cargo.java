package br.edu.famper.api_votos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "descricao", length = 250)
    private String descricao;

    @OneToMany(mappedBy = "cargo", targetEntity = Candidato.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Set<Candidato> candidatos;
}
