package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "campus",
        uniqueConstraints =
        @UniqueConstraint(
                columnNames = {"id_ies", "id_cidade"}
        ))
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campus")
    private Long idCampus;

    @ManyToOne
    @JoinColumn(name = "id_ies", nullable = false)
    private InstituicaoEnsinoSuperior instituicaoEnsinoSuperior;

    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    public Campus() {
    }

    public Campus(Long idCampus, InstituicaoEnsinoSuperior instituicaoEnsinoSuperior, Cidade cidade) {
        this.idCampus = idCampus;
        this.instituicaoEnsinoSuperior = instituicaoEnsinoSuperior;
        this.cidade = cidade;
    }

    public Campus(Cidade cidade, InstituicaoEnsinoSuperior instituicaoEnsinoSuperior) {
        this.instituicaoEnsinoSuperior = instituicaoEnsinoSuperior;
        this.cidade = cidade;
    }

    public Long getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(Long idCampus) {
        this.idCampus = idCampus;
    }

    public InstituicaoEnsinoSuperior getInstituicaoEnsinoSuperior() {
        return instituicaoEnsinoSuperior;
    }

    public void setInstituicaoEnsinoSuperior(InstituicaoEnsinoSuperior instituicaoEnsinoSuperior) {
        this.instituicaoEnsinoSuperior = instituicaoEnsinoSuperior;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campus campus = (Campus) o;
        return Objects.equals(idCampus, campus.idCampus) &&
                Objects.equals(instituicaoEnsinoSuperior, campus.instituicaoEnsinoSuperior) &&
                Objects.equals(cidade, campus.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampus, instituicaoEnsinoSuperior, cidade);
    }

    @Override
    public String toString() {
        return "Campus{" +
                "idCampus=" + idCampus +
                ", instituicaoEnsinoSuperior=" + instituicaoEnsinoSuperior +
                ", cidade=" + cidade +
                '}';
    }
}
