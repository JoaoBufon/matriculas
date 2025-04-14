package br.ucs.matriculas.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "instituicao_ensino_superior")
public class InstituicaoEnsinoSuperior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ies")
    private Long idIES;

    @Column(name = "des_ies")
    private String desIES;

    @Column(name = "sigla_ies")
    private String siglaIES;

    @Column(name = "organizacao")
    private String organizacao;

    @Column(name = "categoria_administrativa")
    private String categoriaAdministrativa;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    public InstituicaoEnsinoSuperior(Long idIES, String desIES, String siglaIES, String organizacao, String categoriaAdministrativa, Cidade cidade) {
        this.idIES = idIES;
        this.desIES = desIES;
        this.siglaIES = siglaIES;
        this.organizacao = organizacao;
        this.categoriaAdministrativa = categoriaAdministrativa;
        this.cidade = cidade;
    }

    public InstituicaoEnsinoSuperior() {
    }

    public Long getIdIES() {
        return idIES;
    }

    public void setIdIES(Long idIES) {
        this.idIES = idIES;
    }

    public String getDesIES() {
        return desIES;
    }

    public void setDesIES(String desIES) {
        this.desIES = desIES;
    }

    public String getSiglaIES() {
        return siglaIES;
    }

    public void setSiglaIES(String siglaIES) {
        this.siglaIES = siglaIES;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    public String getCategoriaAdministrativa() {
        return categoriaAdministrativa;
    }

    public void setCategoriaAdministrativa(String categoriaAdministrativa) {
        this.categoriaAdministrativa = categoriaAdministrativa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InstituicaoEnsinoSuperior that = (InstituicaoEnsinoSuperior) o;
        return Objects.equals(idIES, that.idIES) && Objects.equals(desIES, that.desIES) && Objects.equals(siglaIES, that.siglaIES) && Objects.equals(organizacao, that.organizacao) && Objects.equals(categoriaAdministrativa, that.categoriaAdministrativa) && Objects.equals(cidade, that.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIES, desIES, siglaIES, organizacao, categoriaAdministrativa, cidade);
    }

    @Override
    public String toString() {
        return "InstituicaoEnsinoSuperior{" +
                "idIES=" + idIES +
                ", desIES='" + desIES + '\'' +
                ", siglaIES='" + siglaIES + '\'' +
                ", organizacao='" + organizacao + '\'' +
                ", categoriaAdministrativa='" + categoriaAdministrativa + '\'' +
                ", cidade=" + cidade +
                '}';
    }
}
