package br.edu.famper.api_votos.repository;

import br.edu.famper.api_votos.model.Eleicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleicaoRepository extends JpaRepository<Eleicao, Long> {
}
