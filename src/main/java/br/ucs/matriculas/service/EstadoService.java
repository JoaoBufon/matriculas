package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.Estado;
import br.ucs.matriculas.repository.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> findAll() {
        return this.estadoRepository.findAll();
    }

    public Optional<Estado> getById(Long id) {
        return this.estadoRepository.findById(id);
    }

    public Estado save(Estado estado) {
        return estadoRepository.save(estado);
    }

    public ResponseEntity<Void> delete(Long idEstado) {
        this.estadoRepository.deleteById(idEstado);
        return ResponseEntity.noContent().build();
    }
}
