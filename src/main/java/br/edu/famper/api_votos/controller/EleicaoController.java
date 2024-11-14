package br.edu.famper.api_votos.controller;

import br.edu.famper.api_votos.dto.EleicaoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Eleicao;
import br.edu.famper.api_votos.service.EleicaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/eleicao")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Eleicao", description = "Operações para eleições")
public class EleicaoController {

    private final EleicaoService eleicaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todas as eleições", description = "Obtém todas as eleições do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<EleicaoDto> getAllEleicoes() {
        log.info("Buscando todas as eleições");
        return eleicaoService.getAllEleicoes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar eleição por ID", description = "Obtém uma eleição específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Eleição não encontrada")
    })
    public ResponseEntity<EleicaoDto> getEleicaoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando eleição por ID: {}", id);
        return ResponseEntity.ok().body(eleicaoService.getEleicaoById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar eleição", description = "Cadastra uma nova eleição no banco de dados")
    public Eleicao createEleicao(@RequestBody EleicaoDto eleicaoDto) {
        log.info("Cadastro de eleição: {}", eleicaoDto);
        return eleicaoService.saveEleicao(eleicaoDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar eleição", description = "Atualiza uma eleição existente no banco de dados")
    public ResponseEntity<EleicaoDto> updateEleicao(@PathVariable(name = "id") Long id, @RequestBody EleicaoDto eleicaoDto) throws ResourceNotFoundException {
        log.info("Atualizando eleição: {}", eleicaoDto);
        return ResponseEntity.ok(eleicaoService.editEleicao(id, eleicaoDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover eleição", description = "Remove uma eleição do banco de dados")
    public Map<String, Boolean> deleteEleicao(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando eleição com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", eleicaoService.deleteEleicao(id));
        return response;
    }
}
