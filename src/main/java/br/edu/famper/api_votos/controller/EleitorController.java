package br.edu.famper.api_votos.controller;

import br.edu.famper.api_votos.dto.EleitorDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Eleitor;
import br.edu.famper.api_votos.service.EleitorService;
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
@RequestMapping("/api/eleitor")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Eleitor", description = "Operações para eleitores")
public class EleitorController {

    private final EleitorService eleitorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os eleitores", description = "Obtém todos os eleitores do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<EleitorDto> getAllEleitores() {
        log.info("Buscando todos os eleitores");
        return eleitorService.getAllEleitores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar eleitor por ID", description = "Obtém um eleitor específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Eleitor não encontrado")
    })
    public ResponseEntity<EleitorDto> getEleitorById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando eleitor por ID: {}", id);
        return ResponseEntity.ok().body(eleitorService.getEleitorById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar eleitor", description = "Cadastra um novo eleitor no banco de dados")
    public Eleitor createEleitor(@RequestBody EleitorDto eleitorDto) {
        log.info("Cadastro de eleitor: {}", eleitorDto);
        return eleitorService.saveEleitor(eleitorDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar eleitor", description = "Atualiza um eleitor existente no banco de dados")
    public ResponseEntity<EleitorDto> updateEleitor(@PathVariable(name = "id") Long id, @RequestBody EleitorDto eleitorDto) throws ResourceNotFoundException {
        log.info("Atualizando eleitor: {}", eleitorDto);
        return ResponseEntity.ok(eleitorService.editEleitor(id, eleitorDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover eleitor", description = "Remove um eleitor do banco de dados")
    public Map<String, Boolean> deleteEleitor(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando eleitor com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", eleitorService.deleteEleitor(id));
        return response;
    }
}
