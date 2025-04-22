package br.ucs.matriculas.controller;

import br.ucs.matriculas.dto.CamposCsvDTO;
import br.ucs.matriculas.service.CsvImportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CsvImportController {

    private final CsvImportService csvImportService;

    public CsvImportController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @GetMapping
    public void lerCsv(@RequestParam(required = false, defaultValue = "..\\Matriculados Brasil - Projeto\\Matriculados Brasil - Projeto.csv") String caminhoCsv){
        this.csvImportService.importarCsv(caminhoCsv);
    }
}
