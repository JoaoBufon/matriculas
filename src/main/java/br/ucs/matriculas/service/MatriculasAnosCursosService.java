package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.MatriculasAnosCursos;
import br.ucs.matriculas.repository.MatriculasAnosCursosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculasAnosCursosService {
    private final MatriculasAnosCursosRepository matriculasAnosCursosRepository;

    public MatriculasAnosCursosService(MatriculasAnosCursosRepository matriculasAnosCursosRepository) {
        this.matriculasAnosCursosRepository = matriculasAnosCursosRepository;
    }

    public List<MatriculasAnosCursos> findAll(){
        return this.matriculasAnosCursosRepository.findAll();
    }

    public Optional<MatriculasAnosCursos> findById(Long id){
        return this.matriculasAnosCursosRepository.findById(id);
    }

    public MatriculasAnosCursos save(MatriculasAnosCursos matriculasAnosCursos){
        return this.matriculasAnosCursosRepository.save(matriculasAnosCursos);
    }

    public List<MatriculasAnosCursos> saveAllInBatch(List<MatriculasAnosCursos> listMatriculasAnosCursos){
        return matriculasAnosCursosRepository.saveAllAndFlush(listMatriculasAnosCursos);
    }

    public ResponseEntity<Void> delete(Long idMatriculasAnosCursos) {
        this.matriculasAnosCursosRepository.deleteById(idMatriculasAnosCursos);
        return ResponseEntity.noContent().build();
    }
}
