package br.ucs.matriculas.dto;

import java.util.Objects;

public class RankingCursos {
    private Long idCurso;
    private String desCurso;
    private Long nmrMatriculados;

    public RankingCursos() {
    }

    public RankingCursos(Long id, String desCurso, Long nmrMatriculados) {
        this.idCurso = id;
        this.desCurso = desCurso;
        this.nmrMatriculados = nmrMatriculados;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getDesCurso() {
        return desCurso;
    }

    public void setDesCurso(String desCurso) {
        this.desCurso = desCurso;
    }

    public Long getNmrMatriculados() {
        return nmrMatriculados;
    }

    public void setNmrMatriculados(Long nmrMatriculados) {
        this.nmrMatriculados = nmrMatriculados;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RankingCursos that = (RankingCursos) o;
        return Objects.equals(idCurso, that.idCurso) && Objects.equals(desCurso, that.desCurso) && Objects.equals(nmrMatriculados, that.nmrMatriculados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, desCurso, nmrMatriculados);
    }

    @Override
    public String toString() {
        return "RankingCursos{" +
                "idCurso=" + idCurso +
                ", desCurso='" + desCurso + '\'' +
                ", nmrMatriculados='" + nmrMatriculados + '\'' +
                '}';
    }
}
