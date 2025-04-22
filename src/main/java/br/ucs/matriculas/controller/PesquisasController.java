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
        return ResponseEntity.ok(this.pesquisasService.getTotalAlunos(ano, null));
    }

    @GetMapping("/totalAlunos/{ano}/{modalidade}")
    public ResponseEntity<Long> getTotalAlunos(@PathVariable final int ano, @PathVariable final String modalidade){
        return ResponseEntity.ok(this.pesquisasService.getTotalAlunos(ano, modalidade));
    }

    @GetMapping("/rankingCursos")
    public ResponseEntity<List<RankingCursos>> getRankingCursos(){
        return ResponseEntity.ok(this.pesquisasService.getRankingCursos(null));
    }

    @GetMapping("/rankingCursos/{modalidade}")
    public ResponseEntity<List<RankingCursos>> getRankingCursos(@PathVariable final String modalidade){
        return ResponseEntity.ok(this.pesquisasService.getRankingCursos(modalidade));
    }
}
