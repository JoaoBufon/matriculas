package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.InstituicaoEnsinoSuperior;
import br.ucs.matriculas.repository.InstituicaoEnsinoSuperiorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoEnsinoSuperiorService {
    private final InstituicaoEnsinoSuperiorRepository instituicaoEnsinoSuperiorRepository;

    public InstituicaoEnsinoSuperiorService(InstituicaoEnsinoSuperiorRepository instituicaoEnsinoSuperiorRepository) {
        this.instituicaoEnsinoSuperiorRepository = instituicaoEnsinoSuperiorRepository;
    }

    public List<InstituicaoEnsinoSuperior> findAll() {
        return this.instituicaoEnsinoSuperiorRepository.findAll();
    }

    public Optional<InstituicaoEnsinoSuperior> findById(Long id) {
        return this.instituicaoEnsinoSuperiorRepository.findById(id);
    }

    public InstituicaoEnsinoSuperior save(InstituicaoEnsinoSuperior instituicaoEnsinoSuperior) {
        return instituicaoEnsinoSuperiorRepository.save(instituicaoEnsinoSuperior);
    }

    public ResponseEntity<Void> delete(Long idInstituicaoEnsinoSuperior) {
        this.instituicaoEnsinoSuperiorRepository.deleteById(idInstituicaoEnsinoSuperior);
        return ResponseEntity.noContent().build();
    }
}
