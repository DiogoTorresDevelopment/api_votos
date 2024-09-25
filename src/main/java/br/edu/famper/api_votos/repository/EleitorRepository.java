package br.edu.famper.api_votos.repository;

import br.edu.famper.api_votos.model.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, Long> {
}
