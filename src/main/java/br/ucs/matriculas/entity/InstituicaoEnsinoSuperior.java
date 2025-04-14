package br.ucs.matriculas.entity;

import jakarta.persistence.*;

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

    public InstituicaoEnsinoSuperior(Long idIES, String desIES, String siglaIES, String organizacao, String categoriaAdministrativa) {
        this.idIES = idIES;
        this.desIES = desIES;
        this.siglaIES = siglaIES;
        this.organizacao = organizacao;
        this.categoriaAdministrativa = categoriaAdministrativa;
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
}
