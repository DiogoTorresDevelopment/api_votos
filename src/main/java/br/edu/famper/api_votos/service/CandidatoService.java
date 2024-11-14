package br.edu.famper.api_votos.service;

import br.edu.famper.api_votos.dto.CandidatoDto;
import br.edu.famper.api_votos.dto.CargoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Candidato;
import br.edu.famper.api_votos.model.Cargo;
import br.edu.famper.api_votos.repository.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidatoService {


    @Autowired
    private final CandidatoRepository candidatoRepository;

    public List<CandidatoDto> getAllCandidatos() {
        return candidatoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CandidatoDto getCandidatoById(Long id) throws ResourceNotFoundException {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato não encontrado com ID: " + id));
        return convertToDto(candidato);
    }

    public Candidato saveCandidato(CandidatoDto candidatoDto) {
        Candidato candidato = convertToEntity(candidatoDto);
        return candidatoRepository.save(candidato);
    }

    public CandidatoDto editCandidato(Long id, CandidatoDto candidatoDto) throws ResourceNotFoundException {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato não encontrado com ID: " + id));
        candidato.setNome(candidatoDto.getNome());
        candidato.setPartido(candidatoDto.getPartido());
        candidato.setCargo(convertToEntity(candidatoDto.getCargo()));
        return convertToDto(candidatoRepository.save(candidato));
    }

    public boolean deleteCandidato(Long id) throws ResourceNotFoundException {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato não encontrado com ID: " + id));
        candidatoRepository.delete(candidato);
        return true;
    }

    private CandidatoDto convertToDto(Candidato candidato) {
        return CandidatoDto.builder()
                .id(candidato.getId())
                .nome(candidato.getNome())
                .partido(candidato.getPartido())
                .cargo(convertToDto(candidato.getCargo()))
                .build();
    }

    private Candidato convertToEntity(CandidatoDto candidatoDto) {
        Candidato candidato = new Candidato();
        candidato.setId(candidatoDto.getId());
        candidato.setNome(candidatoDto.getNome());
        candidato.setPartido(candidatoDto.getPartido());
        candidato.setCargo(convertToEntity(candidatoDto.getCargo()));
        return candidato;
    }

    private CargoDto convertToDto(Cargo cargo) {
        if (cargo == null) return null;

        return CargoDto.builder()
                .id(cargo.getId())
                .nome(cargo.getNome())
                .descricao(cargo.getDescricao())
                .build();
    }

    private Cargo convertToEntity(CargoDto cargoDto) {
        if (cargoDto == null) return null;

        Cargo cargo = new Cargo();
        cargo.setId(cargoDto.getId());
        cargo.setNome(cargoDto.getNome());
        cargo.setDescricao(cargoDto.getDescricao());
        return cargo;
    }

}
