package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.Curso;
import br.ucs.matriculas.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.ok(this.cursoService.findAll());
    }


    @GetMapping ( value = "/{idCurso}")
    public ResponseEntity<Optional<Curso>> findById(@PathVariable final Long idCurso){
        return ResponseEntity.ok(this.cursoService.findById(idCurso));
    }

    @PostMapping
    public Curso save(@RequestBody final Curso curso){
        return this.cursoService.save(curso);
    }

    @DeleteMapping( value = "/{idCurso}")
    public ResponseEntity<Void> delete(@PathVariable final Long idCurso){
        return this.cursoService.delete(idCurso);
    }
}
