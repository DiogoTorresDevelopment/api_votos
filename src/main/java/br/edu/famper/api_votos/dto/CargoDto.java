package br.edu.famper.api_votos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CargoDto {

    @Schema(description = "ID do Cargo", example = "1", title = "id")
    private Long id;

    @Schema(description = "Nome do Cargo", example = "Presidente", title = "nome", maxLength = 100)
    private String nome;

    @Schema(description = "Descrição do Cargo", example = "Cargo de presidente da república", title = "descricao", maxLength = 250)
    private String descricao;

    @Schema(description = "Lista de Candidatos para o Cargo", title = "candidatos")
    private Set<CandidatoDto> candidatos;
}
