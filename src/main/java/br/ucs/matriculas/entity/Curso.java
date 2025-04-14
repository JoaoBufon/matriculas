package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "des_curso")
    private String desCurso;

    @Column(name = "des_detalhada_curso")
    private String desDetalhadaCurso;

    @Column(name = "modalidade")
    private String modalidade;

    @Column(name = "grau")
    private String grau;

    public Curso() {
    }

    public Curso(Long idCurso, String desCurso, String desDetalhadaCurso, String modalidade, String grau) {
        this.idCurso = idCurso;
        this.desCurso = desCurso;
        this.desDetalhadaCurso = desDetalhadaCurso;
        this.modalidade = modalidade;
        this.grau = grau;
    }

    public String getDesCurso() {
        return desCurso;
    }

    public void setDesCurso(String desCurso) {
        this.desCurso = desCurso;
    }

    public String getDesDetalhadaCurso() {
        return desDetalhadaCurso;
    }

    public void setDesDetalhadaCurso(String desDetalhadaCurso) {
        this.desDetalhadaCurso = desDetalhadaCurso;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(desCurso, curso.desCurso) && Objects.equals(desDetalhadaCurso, curso.desDetalhadaCurso) && Objects.equals(modalidade, curso.modalidade) && Objects.equals(grau, curso.grau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desCurso, desDetalhadaCurso, modalidade, grau);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "desCurso='" + desCurso + '\'' +
                ", desDetalhadaCurso='" + desDetalhadaCurso + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", grau='" + grau + '\'' +
                '}';
    }
}
