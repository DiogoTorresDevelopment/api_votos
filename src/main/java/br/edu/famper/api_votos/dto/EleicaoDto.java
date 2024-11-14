package br.edu.famper.api_votos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EleicaoDto {

    @Schema(description = "ID da Eleição", example = "1", title = "id")
    private Long id;

    @Schema(description = "Título da Eleição", example = "Eleições Presidenciais 2024", title = "titulo", maxLength = 150)
    private String titulo;

    @Schema(description = "Descrição da Eleição", example = "Eleição para escolher o presidente da república", title = "descricao", maxLength = 500)
    private String descricao;

    @Schema(description = "Data de Início da Eleição", example = "2024-10-01", title = "dataInicio")
    private LocalDate dataInicio;

    @Schema(description = "Data de Fim da Eleição", example = "2024-10-31", title = "dataFim")
    private LocalDate dataFim;

    @Schema(description = "Lista de Candidatos Participantes da Eleição", title = "candidatos")
    private Set<CandidatoDto> candidatos;
}
