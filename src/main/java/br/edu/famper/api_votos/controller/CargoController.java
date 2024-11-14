package br.edu.famper.api_votos.controller;

import br.edu.famper.api_votos.dto.CargoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Cargo;
import br.edu.famper.api_votos.service.CargoService;
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
@RequestMapping("/api/cargo")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cargo", description = "Operações para cargos")
public class CargoController {

    private final CargoService cargoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar todos os cargos", description = "Obtém todos os cargos do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    public List<CargoDto> getAllCargos() {
        log.info("Buscando todos os cargos");
        return cargoService.getAllCargos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar cargo por ID", description = "Obtém um cargo específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
    })
    public ResponseEntity<CargoDto> getCargoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando cargo por ID: {}", id);
        return ResponseEntity.ok().body(cargoService.getCargoById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar cargo", description = "Cadastra um novo cargo no banco de dados")
    public Cargo createCargo(@RequestBody CargoDto cargoDto) {
        log.info("Cadastro de cargo: {}", cargoDto);
        return cargoService.saveCargo(cargoDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar cargo", description = "Atualiza um cargo existente no banco de dados")
    public ResponseEntity<CargoDto> updateCargo(@PathVariable(name = "id") Long id, @RequestBody CargoDto cargoDto) throws ResourceNotFoundException {
        log.info("Atualizando cargo: {}", cargoDto);
        return ResponseEntity.ok(cargoService.editCargo(id, cargoDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover cargo", description = "Remove um cargo do banco de dados")
    public Map<String, Boolean> deleteCargo(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Deletando cargo com ID: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", cargoService.deleteCargo(id));
        return response;
    }
}
