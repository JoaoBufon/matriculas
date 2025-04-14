package br.ucs.matriculas.repository;

import br.ucs.matriculas.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
