package br.ucs.matriculas.dto;

import java.util.Objects;

public class TotalAlunosPorAno {
    private Integer numAno;
    private Long nmrTotalAlunos;

    public TotalAlunosPorAno() {
    }

    public TotalAlunosPorAno(int numAno, long nmrTotalAlunos) {
        this.numAno = numAno;
        this.nmrTotalAlunos = nmrTotalAlunos;
    }

    public int getNumAno() {
        return numAno;
    }

    public void setNumAno(int numAno) {
        this.numAno = numAno;
    }

    public Long getNmrTotalAlunos() {
        return nmrTotalAlunos;
    }

    public void setNmrTotalAlunos(long nmrTotalAlunos) {
        this.nmrTotalAlunos = nmrTotalAlunos;
    }

    // equals() e hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalAlunosPorAno that = (TotalAlunosPorAno) o;
        return Objects.equals(numAno, that.numAno) && Objects.equals(nmrTotalAlunos, that.nmrTotalAlunos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numAno, nmrTotalAlunos);
    }

    // toString()
    @Override
    public String toString() {
        return "TotalAlunosPorAno{" +
                "numAno=" + numAno +
                ", nmrTotalAlunos=" + nmrTotalAlunos +
                '}';
    }
}
