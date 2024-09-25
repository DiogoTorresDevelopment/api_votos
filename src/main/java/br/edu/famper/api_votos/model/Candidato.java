package br.edu.famper.api_votos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "candidato")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "partido", length = 100)
    private String partido; // Corrigido o nome do atributo

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

}
