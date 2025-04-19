package br.ucs.matriculas.controller;

import br.ucs.matriculas.dto.CamposCsvDTO;
import br.ucs.matriculas.service.CsvImportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public void lerCsv(){
        this.csvImportService.importarCsv("C:\\Users\\joaob\\OneDrive\\Documentos\\projeto e arquitura de software\\Matriculados Brasil - Projeto", true, ";");
    }
}
