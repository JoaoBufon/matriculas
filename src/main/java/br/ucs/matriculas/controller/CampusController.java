package br.ucs.matriculas.controller;

import br.ucs.matriculas.entity.Campus;
import br.ucs.matriculas.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campus")
public class CampusController {

    private CampusService campusService;

    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @GetMapping
    public ResponseEntity<List<Campus>> findAll() {
        return ResponseEntity.ok(this.campusService.getAllCampuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campus> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.campusService.findCampusById(id));
    }

    @PostMapping
    public ResponseEntity<Campus> save(@RequestBody Campus campus) {
        return ResponseEntity.ok(this.campusService.saveCampus(campus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.campusService.deleteCampusById(id);
        return ResponseEntity.noContent().build();
    }
}
