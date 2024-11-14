package br.edu.famper.api_votos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatoDto {

    @Schema(description = "ID do Candidato", example = "1", title = "id")
    private Long id;

    @Schema(description = "Nome do Candidato", example = "Pedro", title = "nome", maxLength = 150)
    private String nome;

    @Schema(description = "Partido do Candidato", example = "PRPC", title = "partido", maxLength = 100)
    private String partido;

    @Schema(description = "Cargo do Candidato", title = "cargo")
    private CargoDto cargo;
}
