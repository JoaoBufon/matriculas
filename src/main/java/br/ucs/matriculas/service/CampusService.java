package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.Campus;
import br.ucs.matriculas.repository.CampusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampusService {

    private final CampusRepository campusRepository;

    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    public List<Campus> getAllCampuses() {
        return campusRepository.findAll();
    }

    public Campus findCampusById(Long id) {
        return campusRepository.findById(id).orElse(null);
    }

    public void deleteCampusById(Long id) {
        campusRepository.deleteById(id);
    }

    public Campus saveCampus(Campus campus) {
        return campusRepository.save(campus);
    }

    public List<Campus> saveAllCampusesInBatch(List<Campus> listCampus) {
        return campusRepository.saveAll(listCampus);
    }
}
