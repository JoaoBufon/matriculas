package br.ucs.matriculas.repository;

import br.ucs.matriculas.entity.MatriculasAnosCursos;
import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface MatriculasAnosCursosRepository extends JpaRepository<MatriculasAnosCursos, Long> {
}
