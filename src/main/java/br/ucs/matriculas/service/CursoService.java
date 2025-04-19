package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.Cidade;
import br.ucs.matriculas.entity.Curso;
import br.ucs.matriculas.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> findAll(){
        return this.cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id){
        return this.cursoRepository.findById(id);
    }

    public Curso save(Curso curso){
        return this.cursoRepository.save(curso);
    }

    public List<Curso> saveAllInBatch(List<Curso> listCursos) {
        return cursoRepository.saveAll(listCursos);
    }

    public ResponseEntity<Void> delete(Long idCurso) {
        this.cursoRepository.deleteById(idCurso);
        return ResponseEntity.noContent().build();
    }
}
