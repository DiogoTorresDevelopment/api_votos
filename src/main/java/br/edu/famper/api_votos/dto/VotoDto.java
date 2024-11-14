package br.edu.famper.api_votos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoDto {

    @Schema(description = "ID do Voto", example = "1", title = "id")
    private Long id;

    @Schema(description = "Eleitor que realizou o Voto", title = "eleitor")
    private EleitorDto eleitor;

    @Schema(description = "Candidato escolhido no Voto", title = "candidato")
    private CandidatoDto candidato;

    @Schema(description = "Eleição na qual o Voto foi realizado", title = "eleicao")
    private EleicaoDto eleicao;
}
