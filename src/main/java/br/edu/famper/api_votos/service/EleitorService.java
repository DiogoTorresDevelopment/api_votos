package br.edu.famper.api_votos.service;

import br.edu.famper.api_votos.dto.EleitorDto;
import br.edu.famper.api_votos.dto.VotoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Eleitor;
import br.edu.famper.api_votos.model.Voto;
import br.edu.famper.api_votos.repository.EleitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EleitorService {

    private final EleitorRepository eleitorRepository;

    public List<EleitorDto> getAllEleitores() {
        return eleitorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EleitorDto getEleitorById(Long id) throws ResourceNotFoundException {
        Eleitor eleitor = eleitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleitor não encontrado com ID: " + id));
        return convertToDto(eleitor);
    }

    public Eleitor saveEleitor(EleitorDto eleitorDto) {
        Eleitor eleitor = convertToEntity(eleitorDto);
        return eleitorRepository.save(eleitor);
    }

    public EleitorDto editEleitor(Long id, EleitorDto eleitorDto) throws ResourceNotFoundException {
        Eleitor eleitor = eleitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleitor não encontrado com ID: " + id));
        eleitor.setNome(eleitorDto.getNome());
        eleitor.setCpf(eleitorDto.getCpf());
        eleitor.setEmail(eleitorDto.getEmail());
        return convertToDto(eleitorRepository.save(eleitor));
    }

    public boolean deleteEleitor(Long id) throws ResourceNotFoundException {
        Eleitor eleitor = eleitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleitor não encontrado com ID: " + id));
        eleitorRepository.delete(eleitor);
        return true;
    }

    private EleitorDto convertToDto(Eleitor eleitor) {
        Set<VotoDto> votosDto = eleitor.getVotos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet());

        return EleitorDto.builder()
                .id(eleitor.getId())
                .nome(eleitor.getNome())
                .cpf(eleitor.getCpf())
                .email(eleitor.getEmail())
                .votos(votosDto)
                .build();
    }

    private Eleitor convertToEntity(EleitorDto eleitorDto) {
        Eleitor eleitor = new Eleitor();
        eleitor.setId(eleitorDto.getId());
        eleitor.setNome(eleitorDto.getNome());
        eleitor.setCpf(eleitorDto.getCpf());
        eleitor.setEmail(eleitorDto.getEmail());
        eleitor.setVotos(eleitorDto.getVotos().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet()));
        return eleitor;
    }

    private VotoDto convertToDto(Voto voto) {
        return VotoDto.builder()
                .id(voto.getId())
                .build();
    }

    private Voto convertToEntity(VotoDto votoDto) {
        Voto voto = new Voto();
        voto.setId(votoDto.getId());
        return voto;
    }
}
