package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "estado",
        uniqueConstraints =
        @UniqueConstraint(
                columnNames = {"des_estado"}
        ))
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Long idEstado;
    @Column(name = "des_estado", nullable = false)
    private String desEstado;

    public Estado(Long idEstado, String desEstado) {
        this.idEstado = idEstado;
        this.desEstado = desEstado;
    }

    public Estado() {
    }

    public Estado(String desEstado) {
        this.desEstado = desEstado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long id) {
        this.idEstado = id;
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(idEstado, estado.idEstado) && Objects.equals(desEstado, estado.desEstado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstado, desEstado);
    }
}
