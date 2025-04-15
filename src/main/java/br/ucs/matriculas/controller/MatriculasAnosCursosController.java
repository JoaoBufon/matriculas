package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.Curso;
import br.ucs.matriculas.entity.MatriculasAnosCursos;
import br.ucs.matriculas.service.CursoService;
import br.ucs.matriculas.service.MatriculasAnosCursosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriculasAnosCursos")
public class MatriculasAnosCursosController {
    private final MatriculasAnosCursosService matriculasAnosCursosService;

    public MatriculasAnosCursosController(MatriculasAnosCursosService matriculasAnosCursosService) {
        this.matriculasAnosCursosService = matriculasAnosCursosService;
    }

    @GetMapping
    public ResponseEntity<List<MatriculasAnosCursos>> findAll(){
        return ResponseEntity.ok(this.matriculasAnosCursosService.findAll());
    }


    @GetMapping ( value = "/{idMatriculasAnosCursos}")
    public ResponseEntity<Optional<MatriculasAnosCursos>> findById(@PathVariable final Long idMatriculasAnosCursos){
        return ResponseEntity.ok(this.matriculasAnosCursosService.findById(idMatriculasAnosCursos));
    }

    @PostMapping
    public MatriculasAnosCursos save(@RequestBody final MatriculasAnosCursos matriculasAnosCursos){
        return this.matriculasAnosCursosService.save(matriculasAnosCursos);
    }

    @DeleteMapping( value = "/{idMatriculasAnosCursos}")
    public ResponseEntity<Void> delete(@RequestBody final Long idMatriculasAnosCursos){
        return this.matriculasAnosCursosService.delete(idMatriculasAnosCursos);
    }
}
