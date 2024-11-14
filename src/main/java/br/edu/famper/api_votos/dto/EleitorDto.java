package br.edu.famper.api_votos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EleitorDto {

    @Schema(description = "ID do Eleitor", example = "1", title = "id")
    private Long id;

    @Schema(description = "Nome do Eleitor", example = "João da Silva", title = "nome", maxLength = 150)
    private String nome;

    @Schema(description = "CPF do Eleitor", example = "12345678901", title = "cpf", maxLength = 11)
    private String cpf;

    @Schema(description = "E-mail do Eleitor", example = "joao.silva@example.com", title = "email", maxLength = 100)
    private String email;

    @Schema(description = "Lista de Votos do Eleitor", title = "votos")
    private Set<VotoDto> votos;
}
