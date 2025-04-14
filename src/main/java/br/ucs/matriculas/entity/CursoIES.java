package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class CursoIES {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_cursoIES")
    private Long idCursoIES;

    @ManyToOne
    @JoinColumn(name = "id_ies")
    private InstituicaoEnsinoSuperior idIES;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso idCurso;

    public CursoIES() {
    }

    public CursoIES(Curso idCurso, InstituicaoEnsinoSuperior idIES, Long idCursoIES) {
        this.idCurso = idCurso;
        this.idIES = idIES;
        this.idCursoIES = idCursoIES;
    }

    public Long getIdCursoIES() {
        return idCursoIES;
    }

    public void setIdCursoIES(Long idCursoIES) {
        this.idCursoIES = idCursoIES;
    }

    public InstituicaoEnsinoSuperior getIdIES() {
        return idIES;
    }

    public void setIdIES(InstituicaoEnsinoSuperior idIES) {
        this.idIES = idIES;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CursoIES cursoIES = (CursoIES) o;
        return Objects.equals(idCursoIES, cursoIES.idCursoIES) && Objects.equals(idIES, cursoIES.idIES) && Objects.equals(idCurso, cursoIES.idCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCursoIES, idIES, idCurso);
    }

    @Override
    public String toString() {
        return "CursoIES{" +
                "idCursoIES=" + idCursoIES +
                ", idIES=" + idIES +
                ", idCurso=" + idCurso +
                '}';
    }
}
