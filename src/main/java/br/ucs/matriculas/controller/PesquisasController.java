package br.ucs.matriculas.controller;

import br.ucs.matriculas.dto.RankingCursos;
import br.ucs.matriculas.service.PesquisasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pesquisas")
public class PesquisasController {

    private final PesquisasService pesquisasService;

    public PesquisasController(PesquisasService pesquisasService) {
        this.pesquisasService = pesquisasService;
    }

    @GetMapping("/totalAlunos/{ano}")
    public ResponseEntity<Long> getTotalAlunos(@PathVariable final int ano){
        return ResponseEntity.ok(this.pesquisasService.getTotalAlunos(ano, null, null));
    }

    @GetMapping("/totalAlunos/{ano}/{modalidade}")
    public ResponseEntity<Long> getTotalAlunos(@PathVariable final int ano, @PathVariable final String modalidade){
        return ResponseEntity.ok(this.pesquisasService.getTotalAlunos(ano, modalidade, null));
    }

    @GetMapping("/totalAlunos/{ano}/{modalidade}/{estado}")
    public ResponseEntity<Long> getTotalAlunos(@PathVariable final int ano, @PathVariable final String modalidade, @PathVariable final String estado){
        return ResponseEntity.ok(this.pesquisasService.getTotalAlunos(ano, modalidade, estado));
    }

    @GetMapping("/rankingCursos/{ano}")
    public ResponseEntity<List<RankingCursos>> getRankingCursos(@PathVariable final int ano){
        return ResponseEntity.ok(this.pesquisasService.getRankingCursos(ano, null, null));
    }

    @GetMapping("/rankingCursos/{ano}/{modalidade}")
    public ResponseEntity<List<RankingCursos>> getRankingCursos(@PathVariable final int ano, @PathVariable final String modalidade){
        return ResponseEntity.ok(this.pesquisasService.getRankingCursos(ano, modalidade, null));
    }

    @GetMapping("/rankingCursos/{ano}/{modalidade}/{estado}")
    public ResponseEntity<List<RankingCursos>> getRankingCursos(@PathVariable final int ano, @PathVariable final String modalidade,  @PathVariable final String estado){
        return ResponseEntity.ok(this.pesquisasService.getRankingCursos(ano, modalidade, estado));
    }
}
