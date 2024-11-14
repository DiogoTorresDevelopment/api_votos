package br.edu.famper.api_votos.service;

import br.edu.famper.api_votos.dto.*;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.*;
import br.edu.famper.api_votos.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VotoService {

    private final VotoRepository votoRepository;

    public List<VotoDto> getAllVotos() {
        return votoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public VotoDto getVotoById(Long id) throws ResourceNotFoundException {
        Voto voto = votoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voto não encontrado com ID: " + id));
        return convertToDto(voto);
    }

    public Voto saveVoto(VotoDto votoDto) {
        Voto voto = convertToEntity(votoDto);
        return votoRepository.save(voto);
    }

    public VotoDto editVoto(Long id, VotoDto votoDto) throws ResourceNotFoundException {
        Voto voto = votoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voto não encontrado com ID: " + id));
        return convertToDto(votoRepository.save(voto));
    }

    public boolean deleteVoto(Long id) throws ResourceNotFoundException {
        Voto voto = votoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voto não encontrado com ID: " + id));
        votoRepository.delete(voto);
        return true;
    }

    private VotoDto convertToDto(Voto voto) {
        return VotoDto.builder()
                .id(voto.getId())
                .eleitor(convertEleitorToDto(voto.getEleitor()))
                .candidato(convertCandidatoToDto(voto.getCandidato()))
                .eleicao(convertEleicaoToDto(voto.getEleicao()))
                .build();
    }

    private EleitorDto convertEleitorToDto(Eleitor eleitor) {
        if (eleitor == null) return null;

        return EleitorDto.builder()
                .id(eleitor.getId())
                .nome(eleitor.getNome())
                .cpf(eleitor.getCpf())
                .email(eleitor.getEmail())
                .build();
    }

    private CandidatoDto convertCandidatoToDto(Candidato candidato) {
        if (candidato == null) return null;

        return CandidatoDto.builder()
                .id(candidato.getId())
                .nome(candidato.getNome())
                .partido(candidato.getPartido())
                .cargo(convertCargoToDto(candidato.getCargo()))
                .build();
    }

    private CargoDto convertCargoToDto(Cargo cargo) {
        if (cargo == null) return null;

        return CargoDto.builder()
                .id(cargo.getId())
                .nome(cargo.getNome())
                .descricao(cargo.getDescricao())
                .build();
    }

    private EleicaoDto convertEleicaoToDto(Eleicao eleicao) {
        if (eleicao == null) return null;

        return EleicaoDto.builder()
                .id(eleicao.getId())
                .titulo(eleicao.getTitulo())
                .descricao(eleicao.getDescricao())
                .dataInicio(eleicao.getDataInicio())
                .dataFim(eleicao.getDataFim())
                .build();
    }

    private Voto convertToEntity(VotoDto votoDto) {
        Voto voto = new Voto();
        voto.setId(votoDto.getId());
        return voto;
    }
}
