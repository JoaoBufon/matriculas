package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.InstituicaoEnsinoSuperior;
import br.ucs.matriculas.service.InstituicaoEnsinoSuperiorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/ies")
public class InstituicaoEnsinoSuperiorController {
    private final InstituicaoEnsinoSuperiorService instituicaoEnsinoSuperiorService;

    public InstituicaoEnsinoSuperiorController(InstituicaoEnsinoSuperiorService instituicaoEnsinoSuperiorService) {
        this.instituicaoEnsinoSuperiorService = instituicaoEnsinoSuperiorService;
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoEnsinoSuperior>> findAll(){
        return ResponseEntity.ok(this.instituicaoEnsinoSuperiorService.findAll());
    }

    @GetMapping( value = "/{idIES}")
    public ResponseEntity<Optional<InstituicaoEnsinoSuperior>> findById(@PathVariable final Long idIES){
        return ResponseEntity.ok(this.instituicaoEnsinoSuperiorService.findById(idIES));
    }

    @PostMapping
    public InstituicaoEnsinoSuperior save(@RequestBody final InstituicaoEnsinoSuperior instituicaoEnsinoSuperior){
        return this.instituicaoEnsinoSuperiorService.save(instituicaoEnsinoSuperior);
    }

    @DeleteMapping( value = "/{idIES}")
    public ResponseEntity<Void> delete(@RequestBody final Long idIES){
        return this.instituicaoEnsinoSuperiorService.delete(idIES);
    }
}
