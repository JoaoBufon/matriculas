package br.ucs.matriculas.service;

import br.ucs.matriculas.entity.Cidade;
import br.ucs.matriculas.entity.Estado;
import br.ucs.matriculas.repository.CidadeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public List<Cidade> findAll(){
        return this.cidadeRepository.findAll();
    }

    public Optional<Cidade> findById(Long id){
        return this.cidadeRepository.findById(id);
    }

    public Cidade save(Cidade cidade){
        return this.cidadeRepository.save(cidade);
    }

    public ResponseEntity<Void> delete(Long idCidade) {
        this.cidadeRepository.deleteById(idCidade);
        return ResponseEntity.noContent().build();
    }
}
