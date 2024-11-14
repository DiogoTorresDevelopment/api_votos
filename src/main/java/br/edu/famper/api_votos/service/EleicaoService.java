package br.edu.famper.api_votos.service;

import br.edu.famper.api_votos.dto.CandidatoDto;
import br.edu.famper.api_votos.dto.EleicaoDto;
import br.edu.famper.api_votos.exception.ResourceNotFoundException;
import br.edu.famper.api_votos.model.Candidato;
import br.edu.famper.api_votos.model.Eleicao;
import br.edu.famper.api_votos.repository.EleicaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EleicaoService {

    private final EleicaoRepository eleicaoRepository;

    public List<EleicaoDto> getAllEleicoes() {
        return eleicaoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EleicaoDto getEleicaoById(Long id) throws ResourceNotFoundException {
        Eleicao eleicao = eleicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleição não encontrada com ID: " + id));
        return convertToDto(eleicao);
    }

    public Eleicao saveEleicao(EleicaoDto eleicaoDto) {
        Eleicao eleicao = convertToEntity(eleicaoDto);
        return eleicaoRepository.save(eleicao);
    }

    public EleicaoDto editEleicao(Long id, EleicaoDto eleicaoDto) throws ResourceNotFoundException {
        Eleicao eleicao = eleicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleição não encontrada com ID: " + id));
        eleicao.setTitulo(eleicaoDto.getTitulo());
        eleicao.setDescricao(eleicaoDto.getDescricao());
        eleicao.setDataInicio(eleicaoDto.getDataInicio());
        eleicao.setDataFim(eleicaoDto.getDataFim());
        return convertToDto(eleicaoRepository.save(eleicao));
    }

    public boolean deleteEleicao(Long id) throws ResourceNotFoundException {
        Eleicao eleicao = eleicaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Eleição não encontrada com ID: " + id));
        eleicaoRepository.delete(eleicao);
        return true;
    }

    private EleicaoDto convertToDto(Eleicao eleicao) {
        Set<CandidatoDto> candidatosDto = eleicao.getCandidatos().stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet());

        return EleicaoDto.builder()
                .id(eleicao.getId())
                .titulo(eleicao.getTitulo())
                .descricao(eleicao.getDescricao())
                .dataInicio(eleicao.getDataInicio())
                .dataFim(eleicao.getDataFim())
                .candidatos(candidatosDto)
                .build();
    }

    private Eleicao convertToEntity(EleicaoDto eleicaoDto) {
        Eleicao eleicao = new Eleicao();
        eleicao.setId(eleicaoDto.getId());
        eleicao.setTitulo(eleicaoDto.getTitulo());
        eleicao.setDescricao(eleicaoDto.getDescricao());
        eleicao.setDataInicio(eleicaoDto.getDataInicio());
        eleicao.setDataFim(eleicaoDto.getDataFim());
        eleicao.setCandidatos(eleicaoDto.getCandidatos().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet()));
        return eleicao;
    }

    private CandidatoDto convertToDto(Candidato candidato) {
        return CandidatoDto.builder()
                .id(candidato.getId())
                .nome(candidato.getNome())
                .partido(candidato.getPartido())
                .build();
    }

    private Candidato convertToEntity(CandidatoDto candidatoDto) {
        Candidato candidato = new Candidato();
        candidato.setId(candidatoDto.getId());
        candidato.setNome(candidatoDto.getNome());
        candidato.setPartido(candidatoDto.getPartido());
        return candidato;
    }
}
