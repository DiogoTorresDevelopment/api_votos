package br.edu.famper.api_votos.service;

import br.edu.famper.api_votos.repository.EleitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EleitorService {


    @Autowired
    private final EleitorRepository eleitorRepository;

}