package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "curso",
        uniqueConstraints =
        @UniqueConstraint(
                columnNames = {"des_curso", "des_detalhada_curso", "grau"}
        ))
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(name = "des_curso", nullable = false)
    private String desCurso;

    @Column(name = "des_detalhada_curso")
    private String desDetalhadaCurso;

    @Column(name = "grau")
    private String grau;

    public Curso() {
    }

    public Curso(Long idCurso, String desCurso, String desDetalhadaCurso, String grau) {
        this.idCurso = idCurso;
        this.desCurso = desCurso;
        this.desDetalhadaCurso = desDetalhadaCurso;
        this.grau = grau;
    }

    public Curso(String desCurso, String desDetalhadaCurso, String grau) {
        this.desCurso = desCurso;
        this.desDetalhadaCurso = desDetalhadaCurso;
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
        return Objects.equals(desCurso, curso.desCurso) && Objects.equals(desDetalhadaCurso, curso.desDetalhadaCurso) && Objects.equals(grau, curso.grau);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desCurso, desDetalhadaCurso, grau);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "desCurso='" + desCurso + '\'' +
                ", desDetalhadaCurso='" + desDetalhadaCurso + '\'' +
                ", grau='" + grau + '\'' +
                '}';
    }
}
