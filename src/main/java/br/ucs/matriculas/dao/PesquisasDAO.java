package br.ucs.matriculas.dao;

import br.ucs.matriculas.dto.RankingCursos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PesquisasDAO {

    private EntityManager em;

    public PesquisasDAO(EntityManager em) {
        this.em = em;
    }
    public Long getTotalAlunos(int ano, String modalidade){
        if (ano < 2014 || ano > 2022) {
            throw new IllegalArgumentException("Ano inválido: " + ano);
        }

        String columnName = "ano_" + ano;
        StringBuilder sb = new StringBuilder("SELECT SUM("+ columnName + ") FROM curso_ies");

        if(modalidade != null && !modalidade.isEmpty()){
            sb.append(" WHERE UPPER (modalidade) = '" + modalidade.toUpperCase() + "'");
        }

        Query query = em.createNativeQuery(sb.toString());

        List<Object> list = query.getResultList();

        if(list != null && !list.isEmpty()){
            return (Long) list.get(0);
        }
        return 0L;
    }

    public List<RankingCursos> getRankingCursos(String modalidade){
        StringBuilder sb = new StringBuilder();

        sb.append(" select ci.id_curso, c.des_curso, sum(ano_2022) nmrMatriculados ");
        sb.append(" from curso_ies ci    ");
        sb.append(" join curso c on ci.id_curso = c.id_curso ");

        if (modalidade != null && !modalidade.isEmpty()){
            sb.append(" WHERE UPPER (modalidade) = '" + modalidade.toUpperCase() + "'");
        }

        sb.append(" group by ci.id_curso , c.des_curso ");
        sb.append(" order by sum(ano_2022) desc ");
        sb.append(" limit 10 ");

        Query query = em.createNativeQuery(sb.toString());
        List<Object[]> results = query.getResultList();
        List<RankingCursos> ranking = new ArrayList<>();

        for (Object[] row : results) {
            Long idCurso = ((Number) row[0]).longValue();
            String desCurso = (String) row[1];
            Long nmrMatriculados = ((Number) row[2]).longValue();

            ranking.add(new RankingCursos(idCurso, desCurso, nmrMatriculados));
        }

        return ranking;
    }
}
