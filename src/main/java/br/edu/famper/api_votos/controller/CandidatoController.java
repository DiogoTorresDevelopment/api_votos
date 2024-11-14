package br.edu.famper.api_votos.controller;

import br.edu.famper.api_votos.dto.CandidatoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Candidato;
import br.edu.famper.api_votos.service.CandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/candidato")
@RequiredArgsConstructor
@Slf4j
public class CandidatoController {

    private final CandidatoService candidatoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os candidatos", description = "Obtém todos os candidatos do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<CandidatoDto> getAllCandidatos() {
        log.info("Buscando todos os candidatos");
        return candidatoService.getAllCandidatos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar candidato por ID", description = "Obtém um candidato específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Candidato não encontrado")
    })
    public ResponseEntity<CandidatoDto> getCandidatoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando candidato por ID: {}", id);
        return ResponseEntity.ok().body(candidatoService.getCandidatoById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar candidato", description = "Cadastra um novo candidato no banco de dados")
    public Candidato createCandidato(@RequestBody CandidatoDto candidatoDto) throws ResourceNotFoundException {
        log.info("Cadastro de candidato: {}", candidatoDto);
        return candidatoService.saveCandidato(candidatoDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar candidato", description = "Atualiza um candidato existente no banco de dados")
    public ResponseEntity<CandidatoDto> updateCandidato(@PathVariable(name = "id") Long id, @RequestBody CandidatoDto candidatoDto) throws ResourceNotFoundException {
        log.info("Atualizando candidato: {}", candidatoDto);
        return ResponseEntity.ok(candidatoService.editCandidato(id, candidatoDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover candidato", description = "Remove um candidato do banco de dados")
    public Map<String, Boolean> deleteCandidato(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando candidato com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", candidatoService.deleteCandidato(id));
        return response;
    }

}
