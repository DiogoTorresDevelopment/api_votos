package br.edu.famper.api_votos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "eleitor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Eleitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 11, unique = true, nullable = false)
    private String cpf;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "eleitor", targetEntity = Voto.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Voto> votos;

}
