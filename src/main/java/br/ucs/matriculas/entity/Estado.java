package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    private String desEstado;
    private String sigla;

    public Estado(Long idEstado, String desEstado, String sigla) {
        this.idEstado = idEstado;
        this.desEstado = desEstado;
        this.sigla = sigla;
    }

    public Estado() {
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long id) {
        this.idEstado = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDesEstado() {
        return desEstado;
    }

    public void setDesEstado(String nome) {
        this.desEstado = nome;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "idEstado=" + idEstado +
                ", desEstado='" + desEstado + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(idEstado, estado.idEstado) && Objects.equals(desEstado, estado.desEstado) && Objects.equals(sigla, estado.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstado, desEstado, sigla);
    }
}
