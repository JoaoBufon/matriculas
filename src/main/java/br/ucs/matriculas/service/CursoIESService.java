package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.CursoIES;
import br.ucs.matriculas.repository.CursoIESRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoIESService {
    private final CursoIESRepository cursoIESRepository;

    public CursoIESService(CursoIESRepository cursoIESRepository) {
        this.cursoIESRepository = cursoIESRepository;
    }

    public List<CursoIES> findAll(){
        return this.cursoIESRepository.findAll();
    }

    public Optional<CursoIES> findById(Long id){
        return this.cursoIESRepository.findById(id);
    }

    public CursoIES save(CursoIES cursoIES){
        return this.cursoIESRepository.save(cursoIES);
    }

    public ResponseEntity<Void> delete(Long idCursoIES) {
        this.cursoIESRepository.deleteById(idCursoIES);
        return ResponseEntity.noContent().build();
    }
}
