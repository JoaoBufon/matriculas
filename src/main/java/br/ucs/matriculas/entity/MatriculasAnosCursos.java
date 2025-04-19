package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "matriculas_anos_cursos")
public class MatriculasAnosCursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matriculas_anos_cursos", nullable = false)
    private Long idMatriculasAnosCursos;

    @ManyToOne
    @JoinColumn(name = "id_curso_ies", nullable = false)
    private CursoIES cursoIES;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "quantidade")
    private Integer quantidade;

    public MatriculasAnosCursos() {
    }

    public MatriculasAnosCursos(CursoIES cursoIES, Integer ano, Integer quantidade) {
        this.cursoIES = cursoIES;
        this.ano = ano;
        this.quantidade = quantidade;
    }

    public Long getIdMatriculasAnosCursos() {
        return idMatriculasAnosCursos;
    }

    public void setIdMatriculasAnosCursos(Long idMatriculasAnosCursos) {
        this.idMatriculasAnosCursos = idMatriculasAnosCursos;
    }

    public CursoIES getCursoIES() {
        return cursoIES;
    }

    public void setCursoIES(CursoIES cursoIES) {
        this.cursoIES = cursoIES;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatriculasAnosCursos that = (MatriculasAnosCursos) o;
        return Objects.equals(idMatriculasAnosCursos, that.idMatriculasAnosCursos) && Objects.equals(cursoIES, that.cursoIES) && Objects.equals(ano, that.ano) && Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatriculasAnosCursos, cursoIES, ano, quantidade);
    }

    @Override
    public String toString() {
        return "MatriculasAnosCursos{" +
                "idMatriculasAnosCursos=" + idMatriculasAnosCursos +
                ", cursoIES=" + cursoIES +
                ", ano='" + ano + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
