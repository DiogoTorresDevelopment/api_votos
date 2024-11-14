package br.edu.famper.api_votos.controller;

import br.edu.famper.api_votos.dto.VotoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Voto;
import br.edu.famper.api_votos.service.VotoService;
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
@RequestMapping("/api/voto")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Voto", description = "Operações para votos")
public class VotoController {

    private final VotoService votoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os votos", description = "Obtém todos os votos do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<VotoDto> getAllVotos() {
        log.info("Buscando todos os votos");
        return votoService.getAllVotos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar voto por ID", description = "Obtém um voto específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Voto não encontrado")
    })
    public ResponseEntity<VotoDto> getVotoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando voto por ID: {}", id);
        return ResponseEntity.ok().body(votoService.getVotoById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar voto", description = "Cadastra um novo voto no banco de dados")
    public Voto createVoto(@RequestBody VotoDto votoDto) {
        log.info("Cadastro de voto: {}", votoDto);
        return votoService.saveVoto(votoDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar voto", description = "Atualiza um voto existente no banco de dados")
    public ResponseEntity<VotoDto> updateVoto(@PathVariable(name = "id") Long id, @RequestBody VotoDto votoDto) throws ResourceNotFoundException {
        log.info("Atualizando voto: {}", votoDto);
        return ResponseEntity.ok(votoService.editVoto(id, votoDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover voto", description = "Remove um voto do banco de dados")
    public Map<String, Boolean> deleteVoto(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando voto com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", votoService.deleteVoto(id));
        return response;
    }
}
