package br.ucs.matriculas.repository;

import br.ucs.matriculas.entity.Cidade;
import br.ucs.matriculas.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
