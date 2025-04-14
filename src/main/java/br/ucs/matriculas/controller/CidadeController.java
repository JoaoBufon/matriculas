package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.Cidade;
import br.ucs.matriculas.service.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cidade")
public class CidadeController {
    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> findAll(){
        return ResponseEntity.ok(this.cidadeService.findAll());
    }

    @GetMapping( value = "/{idCidade}")
    public ResponseEntity<Optional<Cidade>> findById(@PathVariable final Long idCidade){
        return ResponseEntity.ok(this.cidadeService.findById(idCidade));
    }

    @PostMapping
    public Cidade save(@RequestBody final Cidade cidade){
        return this.cidadeService.save(cidade);
    }

    @DeleteMapping( value = "/{idCidade}")
    public ResponseEntity<Void> delete(@RequestBody final Long idCidade){
        return this.cidadeService.delete(idCidade);
    }
}
