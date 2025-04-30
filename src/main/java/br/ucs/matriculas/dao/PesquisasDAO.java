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

    public Long getTotalAlunos(int ano, String modalidade, String estado) {
        if (ano < 2014 || ano > 2022) {
            throw new IllegalArgumentException("Ano inválido: " + ano);
        }

        String columnName = "ano_" + ano;
        StringBuilder sb = new StringBuilder("SELECT SUM(" + columnName + ") FROM curso_ies ci");

        if (estado != null && !estado.isEmpty()) {
            sb.append(" JOIN campus camp ON ci.id_campus = camp.id_campus ");
            sb.append(" JOIN cidade cid ON camp.id_cidade = cid.id_cidade");
            sb.append(" JOIN estado est ON cid.id_estado = est.id_estado");
        }

        if (modalidade != null && !modalidade.isEmpty() && !Objects.equals(modalidade, "ALL")) {
            sb.append(" AND UPPER(ci.modalidade) = '" + modalidade.toUpperCase() + "'");
        }

        if (estado != null && !estado.isEmpty()) {
            sb.append(" AND UPPER(est.des_estado) = '" + estado.toUpperCase() + "'");
        }

        Query query = em.createNativeQuery(sb.toString().replaceFirst("AND", "WHERE"));

        List<Object> list = query.getResultList();

        if (list != null && !list.isEmpty()) {
            return (Long) list.getFirst();
        }

        return 0L;
    }

    public List<RankingCursos> getRankingCursos(String modalidade, String estado) {
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ci.id_curso, c.des_curso, sum(ano_2022) nmrMatriculados ");
        sb.append(" FROM curso_ies ci ");
        sb.append(" JOIN curso c ON ci.id_curso = c.id_curso ");
        sb.append(" JOIN campus camp ON camp.id_campus = ci.id_campus ");
        sb.append(" JOIN cidade cid ON camp.id_cidade = cid.id_cidade ");
        sb.append(" JOIN estado est ON cid.id_estado = est.id_estado ");

        if (modalidade != null && !modalidade.isEmpty() && !Objects.equals(modalidade, "ALL")) {
            sb.append(" AND UPPER(ci.modalidade) = '" + modalidade.toUpperCase() + "'");
        }

        if (estado != null && !estado.isEmpty()) {
            sb.append(" AND UPPER(est.des_estado) = '" + estado.toUpperCase() + "'");
        }

        sb.append(" GROUP BY ci.id_curso, c.des_curso ");
        sb.append(" ORDER BY sum(ano_2022) DESC ");
        sb.append(" LIMIT 10 ");

        Query query = em.createNativeQuery(sb.toString().replaceFirst("AND", "WHERE"));
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
