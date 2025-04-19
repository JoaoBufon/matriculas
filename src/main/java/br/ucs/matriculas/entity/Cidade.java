package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cidade",
        uniqueConstraints =
        @UniqueConstraint(
                columnNames = {"des_cidade", "id_estado"}
        ))
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade", nullable = false)
    private Long idCidade;

    @Column(name = "des_cidade", nullable = false)
    private String desCidade;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    public Cidade(Long idCidade, String desCidade, Estado estado) {
        this.idCidade = idCidade;
        this.desCidade = desCidade;
        this.estado = estado;
    }

    public Cidade() {
    }

    public Cidade(String desCidade, Estado estado) {
        this.desCidade = desCidade;
        this.estado = estado;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long id) {
        this.idCidade = id;
    }

    public String getDesCidade() {
        return desCidade;
    }

    public void setDesCidade(String nome) {
        this.desCidade = nome;
    }

    public Estado getEstado() {return estado;}

    public void setEstado(Estado estado) {this.estado = estado;}
    @Override
    public String toString() {
        return "Cidade{" +
                "idCidade=" + idCidade +
                ", desCidade='" + desCidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(idCidade, cidade.idCidade) && Objects.equals(desCidade, cidade.desCidade) && Objects.equals(estado, cidade.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCidade, desCidade, estado);
    }
}
