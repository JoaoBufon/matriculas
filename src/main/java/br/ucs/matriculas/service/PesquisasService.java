package br.ucs.matriculas.service;

import br.ucs.matriculas.dao.PesquisasDAO;
import br.ucs.matriculas.dto.RankingCursos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesquisasService {

    private PesquisasDAO pesquisasDAO;

    public PesquisasService(PesquisasDAO pesquisasDAO) {
        this.pesquisasDAO = pesquisasDAO;
    }
    public Long getTotalAlunos(int ano, String modalidade, String estado){
        return this.pesquisasDAO.getTotalAlunos(ano, modalidade, estado);
    }

    public List<RankingCursos> getRankingCursos(int ano, String modalidade, String estado){
        return this.pesquisasDAO.getRankingCursos(ano, modalidade, estado);
    }
}
