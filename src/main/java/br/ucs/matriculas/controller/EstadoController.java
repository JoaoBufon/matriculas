package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.Estado;
import br.ucs.matriculas.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/estado")
public class EstadoController {
    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> findAll(){
        return ResponseEntity.ok(this.estadoService.findAll());
    }


    @GetMapping ( value = "/{idEstado}")
    public ResponseEntity<Optional<Estado>> findById(@PathVariable final Long idEstado){
        return ResponseEntity.ok(this.estadoService.getById(idEstado));
    }

    @PostMapping
    public Estado save(@RequestBody final Estado estado){
        return this.estadoService.save(estado);
    }

    @DeleteMapping( value = "/{idEstado}")
    public ResponseEntity<Void> delete(@PathVariable final Long idEstado){
        return this.estadoService.delete(idEstado);
    }
}
