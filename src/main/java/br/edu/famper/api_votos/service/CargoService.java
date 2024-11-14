package br.edu.famper.api_votos.service;

import br.edu.famper.api_votos.dto.CargoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Cargo;
import br.edu.famper.api_votos.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CargoService {

    private final CargoRepository cargoRepository;

    public List<CargoDto> getAllCargos() {
        return cargoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CargoDto getCargoById(Long id) throws ResourceNotFoundException {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));
        return convertToDto(cargo);
    }

    public Cargo saveCargo(CargoDto cargoDto) {
        Cargo cargo = convertToEntity(cargoDto);
        return cargoRepository.save(cargo);
    }

    public CargoDto editCargo(Long id, CargoDto cargoDto) throws ResourceNotFoundException {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));
        cargo.setNome(cargoDto.getNome());
        cargo.setDescricao(cargoDto.getDescricao());
        return convertToDto(cargoRepository.save(cargo));
    }

    public boolean deleteCargo(Long id) throws ResourceNotFoundException {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado com ID: " + id));
        cargoRepository.delete(cargo);
        return true;
    }

    private CargoDto convertToDto(Cargo cargo) {
        return CargoDto.builder()
                .id(cargo.getId())
                .nome(cargo.getNome())
                .descricao(cargo.getDescricao())
                .build();
    }

    private Cargo convertToEntity(CargoDto cargoDto) {
        Cargo cargo = new Cargo();
        cargo.setId(cargoDto.getId());
        cargo.setNome(cargoDto.getNome());
        cargo.setDescricao(cargoDto.getDescricao());
        return cargo;
    }
}
