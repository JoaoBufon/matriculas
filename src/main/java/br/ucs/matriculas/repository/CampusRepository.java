package br.ucs.matriculas.repository;

import br.ucs.matriculas.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus, Long> {
}
