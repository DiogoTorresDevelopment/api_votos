package br.edu.famper.api_votos.resource;

import br.edu.famper.api_votos.service.EleicaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cidade")
@RequiredArgsConstructor
@Slf4j
public class EleicaoResource {

    private final EleicaoService eleicaoService;




}
