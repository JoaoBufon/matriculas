package br.ucs.matriculas.dto;

import java.util.Objects;

public class CamposCsvDTO {
    private String desEstado;
    private String desCidade;
    private String desIES;
    private String desSigla;
    private String organizacao;
    private String categoriaAdministrativa;
    private String desCurso;
    private String desDetalhadoCurso;
    private String modalidade;
    private String grau;
    private Integer ano2014;
    private Integer ano2015;
    private Integer ano2016;
    private Integer ano2017;
    private Integer ano2018;
    private Integer ano2019;
    private Integer ano2020;
    private Integer ano2021;
    private Integer ano2022;

    public CamposCsvDTO() {
    }

    public CamposCsvDTO(String desEstado, String desCidade, String desIES, String desSigla, String organização, String categoriaAdministrativa, String desCurso, String desDetalhadoCurso, String modalidade, String grau, Integer ano2014, Integer ano2015, Integer ano2016, Integer ano2017, Integer ano2018, Integer ano2019, Integer ano2020, Integer ano2021, Integer ano2022) {
        this.desEstado = desEstado;
        this.desCidade = desCidade;
        this.desIES = desIES;
        this.desSigla = desSigla;
        this.organizacao = organização;
        this.categoriaAdministrativa = categoriaAdministrativa;
        this.desCurso = desCurso;
        this.desDetalhadoCurso = desDetalhadoCurso;
        this.modalidade = modalidade;
        this.grau = grau;
        this.ano2014 = ano2014;
        this.ano2015 = ano2015;
        this.ano2016 = ano2016;
        this.ano2017 = ano2017;
        this.ano2018 = ano2018;
        this.ano2019 = ano2019;
        this.ano2020 = ano2020;
        this.ano2021 = ano2021;
        this.ano2022 = ano2022;
    }

    public String getDesEstado() {
        return desEstado;
    }

    public void setDesEstado(String desEstado) {
        this.desEstado = desEstado;
    }

    public String getDesCidade() {
        return desCidade;
    }

    public void setDesCidade(String desCidade) {
        this.desCidade = desCidade;
    }

    public String getDesIES() {
        return desIES;
    }

    public void setDesIES(String desIES) {
        this.desIES = desIES;
    }

    public String getDesSigla() {
        return desSigla;
    }

    public void setDesSigla(String desSigla) {
        this.desSigla = desSigla;
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

    public String getDesCurso() {
        return desCurso;
    }

    public void setDesCurso(String desCurso) {
        this.desCurso = desCurso;
    }

    public String getDesDetalhadoCurso() {
        return desDetalhadoCurso;
    }

    public void setDesDetalhadoCurso(String desDetalhadoCurso) {
        this.desDetalhadoCurso = desDetalhadoCurso;
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

    public Integer getAno2014() {
        return ano2014;
    }

    public void setAno2014(Integer ano2014) {
        this.ano2014 = ano2014;
    }

    public Integer getAno2015() {
        return ano2015;
    }

    public void setAno2015(Integer ano2015) {
        this.ano2015 = ano2015;
    }

    public Integer getAno2016() {
        return ano2016;
    }

    public void setAno2016(Integer ano2016) {
        this.ano2016 = ano2016;
    }

    public Integer getAno2017() {
        return ano2017;
    }

    public void setAno2017(Integer ano2017) {
        this.ano2017 = ano2017;
    }

    public Integer getAno2018() {
        return ano2018;
    }

    public void setAno2018(Integer ano2018) {
        this.ano2018 = ano2018;
    }

    public Integer getAno2019() {
        return ano2019;
    }

    public void setAno2019(Integer ano2019) {
        this.ano2019 = ano2019;
    }

    public Integer getAno2020() {
        return ano2020;
    }

    public void setAno2020(Integer ano2020) {
        this.ano2020 = ano2020;
    }

    public Integer getAno2021() {
        return ano2021;
    }

    public void setAno2021(Integer ano2021) {
        this.ano2021 = ano2021;
    }

    public Integer getAno2022() {
        return ano2022;
    }

    public void setAno2022(Integer ano2022) {
        this.ano2022 = ano2022;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CamposCsvDTO that = (CamposCsvDTO) o;
        return Objects.equals(desEstado, that.desEstado) && Objects.equals(desCidade, that.desCidade) && Objects.equals(desIES, that.desIES) && Objects.equals(desSigla, that.desSigla) && Objects.equals(organizacao, that.organizacao) && Objects.equals(categoriaAdministrativa, that.categoriaAdministrativa) && Objects.equals(desCurso, that.desCurso) && Objects.equals(desDetalhadoCurso, that.desDetalhadoCurso) && Objects.equals(modalidade, that.modalidade) && Objects.equals(grau, that.grau) && Objects.equals(ano2014, that.ano2014) && Objects.equals(ano2015, that.ano2015) && Objects.equals(ano2016, that.ano2016) && Objects.equals(ano2017, that.ano2017) && Objects.equals(ano2018, that.ano2018) && Objects.equals(ano2019, that.ano2019) && Objects.equals(ano2020, that.ano2020) && Objects.equals(ano2021, that.ano2021) && Objects.equals(ano2022, that.ano2022);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desEstado, desCidade, desIES, desSigla, organizacao, categoriaAdministrativa, desCurso, desDetalhadoCurso, modalidade, grau, ano2014, ano2015, ano2016, ano2017, ano2018, ano2019, ano2020, ano2021, ano2022);
    }

    @Override
    public String toString() {
        return "CamposCsvDTO{" +
                "desEstado='" + desEstado + '\'' +
                ", desCidade='" + desCidade + '\'' +
                ", desIES='" + desIES + '\'' +
                ", desSigla='" + desSigla + '\'' +
                ", organização='" + organizacao + '\'' +
                ", categoriaAdministrativa='" + categoriaAdministrativa + '\'' +
                ", desCurso='" + desCurso + '\'' +
                ", desDetalhadoCurso='" + desDetalhadoCurso + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", grau='" + grau + '\'' +
                ", ano2014=" + ano2014 +
                ", ano2015=" + ano2015 +
                ", ano2016=" + ano2016 +
                ", ano2017=" + ano2017 +
                ", ano2018=" + ano2018 +
                ", ano2019=" + ano2019 +
                ", ano2020=" + ano2020 +
                ", ano2021=" + ano2021 +
                ", ano2022=" + ano2022 +
                '}';
    }
}
