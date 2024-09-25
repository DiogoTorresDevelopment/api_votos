package br.edu.famper.api_votos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "voto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleitor_id")
    private Eleitor eleitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eleicao_id")
    private Eleicao eleicao;
}
