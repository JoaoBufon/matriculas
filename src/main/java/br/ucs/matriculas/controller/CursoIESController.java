package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.CursoIES;
import br.ucs.matriculas.service.CursoIESService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursoies")
public class CursoIESController {
    private final CursoIESService cursoIESService;

    public CursoIESController(CursoIESService cursoIESService) {
        this.cursoIESService = cursoIESService;
    }

    @GetMapping
    public ResponseEntity<List<CursoIES>> findAll(){
        return ResponseEntity.ok(this.cursoIESService.findAll());
    }


    @GetMapping ( value = "/{idCursoIES}")
    public ResponseEntity<Optional<CursoIES>> findById(@PathVariable final Long idCursoIES){
        return ResponseEntity.ok(this.cursoIESService.findById(idCursoIES));
    }

    @PostMapping
    public CursoIES save(@RequestBody final CursoIES cursoIES){
        return this.cursoIESService.save(cursoIES);
    }

    @DeleteMapping( value = "/{idCursoIES}")
    public ResponseEntity<Void> delete(@PathVariable final Long idCursoIES){
        return this.cursoIESService.delete(idCursoIES);
    }
}
