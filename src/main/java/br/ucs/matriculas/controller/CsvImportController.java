package br.ucs.matriculas.controller;

import br.ucs.matriculas.dto.CamposCsvDTO;
import br.ucs.matriculas.service.CsvImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CsvImportController {

    private final CsvImportService csvImportService;

    public CsvImportController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> lerCsv(@RequestParam("file") MultipartFile file) {
        try {
            this.csvImportService.importarCsv(file);
            return ResponseEntity.ok("CSV importado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao importar o CSV: " + e.getMessage());
        }
    }
}
