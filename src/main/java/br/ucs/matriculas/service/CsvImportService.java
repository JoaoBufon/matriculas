package br.ucs.matriculas.service;

import br.ucs.matriculas.dto.CamposCsvDTO;
import br.ucs.matriculas.entity.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CsvImportService {

    private final EstadoService estadoService;
    private final CidadeService cidadeService;
    private final InstituicaoEnsinoSuperiorService instituicaoEnsinoSuperiorService;
    private final CursoService cursoService;
    private final CursoIESService cursoIESService;
    private final CampusService campusService;
    Logger logger = Logger.getLogger(CsvImportService.class.getName());

    public CsvImportService(EstadoService estadoService, CidadeService cidadeService, InstituicaoEnsinoSuperiorService instituicaoEnsinoSuperiorService, CursoService cursoService, CursoIESService cursoIESService, CampusService campusService) {
        this.estadoService = estadoService;
        this.cidadeService = cidadeService;
        this.instituicaoEnsinoSuperiorService = instituicaoEnsinoSuperiorService;
        this.cursoService = cursoService;
        this.cursoIESService = cursoIESService;
        this.campusService = campusService;
    }

    public void importarCsv(String caminhoCsv, boolean possuiCabecalho, String separador) {
        List<CamposCsvDTO> listCsv = this.leituraCsv(caminhoCsv, possuiCabecalho, separador);

        List<Estado> listEstados = this.saveEstados(listCsv);

        List<Cidade> listCidades = this.saveCidades(listCsv, listEstados);

        List<InstituicaoEnsinoSuperior> listIES = this.saveIes(listCsv);

        List<Curso> listCursos = this.saveCursos(listCsv);

        List<CursoIES> listCursoIes = this.saveCursosIes(listCsv,listCursos, listIES);

        List<Campus> listCampus = this.saveCampuses(listCsv, listCidades, listIES);

        //List<MatriculasAnosCursos> lstMatriculasAnosCursos = this.saveMatriculasAnosCursos(listCsv, listCursoIes);
    }

    private List<Campus> saveCampuses(List<CamposCsvDTO> listCsv, List<Cidade> listCidades, List<InstituicaoEnsinoSuperior> listIES) {
        return campusService.saveAllCampusesInBatch(listCsv.stream().map(dto -> new Campus(
                listCidades.stream().filter(cidade -> Objects.equals(cidade.getDesCidade(), dto.getDesCidade())).findFirst().orElse(null),
                listIES.stream().filter(ies -> Objects.equals(dto.getDesIES(), ies.getDesIES())).findFirst().orElse(null)
        )).distinct().toList());
    }

    private List<CursoIES> saveCursosIes(List<CamposCsvDTO> listCsv, List<Curso> listCursos, List<InstituicaoEnsinoSuperior> listIES) {
        return cursoIESService.saveAllInBatch(listCsv.stream().map(dto -> new CursoIES(
                listCursos.stream().filter(curso -> Objects.equals(dto.getDesCurso(), curso.getDesCurso())).findFirst().orElse(null),
                listIES.stream().filter(ies -> Objects.equals(dto.getDesIES(), ies.getDesIES())).findFirst().orElse(null),
                dto.getModalidade(),
                dto.getAno2014(),
                dto.getAno2015(),
                dto.getAno2016(),
                dto.getAno2017(),
                dto.getAno2018(),
                dto.getAno2019(),
                dto.getAno2020(),
                dto.getAno2021(),
                dto.getAno2022()
        )).distinct().toList());
    }

    private List<Curso> saveCursos(List<CamposCsvDTO> listCsv) {
        return cursoService.saveAllInBatch(listCsv.stream().map(dto -> new Curso(
                dto.getDesCurso(),
                dto.getDesDetalhadoCurso(),
                dto.getGrau()
        )).distinct().toList());
    }

    private List<InstituicaoEnsinoSuperior> saveIes(List<CamposCsvDTO> listCsv) {
        return instituicaoEnsinoSuperiorService.saveAllInBatch(listCsv.stream()
                .map(dto -> new InstituicaoEnsinoSuperior(
                        dto.getDesIES(),
                        dto.getDesSigla(),
                        dto.getOrganizacao(),
                        dto.getCategoriaAdministrativa()
                ))
                .distinct()
                .toList());
    }

    private List<Cidade> saveCidades(List<CamposCsvDTO> listCsv, List<Estado> listEstados) {
        return cidadeService.saveAllInBatch(listCsv.stream().map(dto -> new Cidade(
                dto.getDesCidade(),
                listEstados.stream().filter(estado -> Objects.equals(estado.getDesEstado(), dto.getDesEstado())).findFirst().orElse(null)
        )).distinct().toList());
    }

    private List<Estado> saveEstados(List<CamposCsvDTO> listCsv){
        return estadoService.saveAllInBatch(listCsv.stream().map(CamposCsvDTO::getDesEstado).distinct().map(Estado::new).toList());
    }

    private Integer getQuantidadeDoAno(Integer ano, Integer[] quantidades) {
        int indice = ano - 2014;
        if (indice < 0 || indice >= quantidades.length) {
            return 0;
        }
        return quantidades[indice];
    }


    private List<CamposCsvDTO> leituraCsv(String caminhoCsv, boolean possuiCabecalho, String separador) {

        caminhoCsv = "C:\\Users\\joaob\\IdeaProjects\\Matriculados Brasil - Projeto\\Matriculados Brasil - Projeto.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            if (possuiCabecalho) {
                String header = br.readLine();
            }

            List<CamposCsvDTO> listLinhas = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(separador, -1);
                listLinhas.add(this.parseCamposCsvDTO(campos));
            }

            return listLinhas;
        } catch (IOException e) {
            logger.severe("Erro ao ler o arquivo CSV: " + e.getMessage());
            return Collections.emptyList();
        }

    }

    private CamposCsvDTO parseCamposCsvDTO(String[] campos) {
        return new CamposCsvDTO(campos[0],
                campos[1],
                campos[2],
                campos[3],
                campos[4],
                campos[5],
                campos[6],
                campos[7],
                campos[8],
                campos[9],
                this.parseInteger(campos[10]),
                this.parseInteger(campos[11]),
                this.parseInteger(campos[12]),
                this.parseInteger(campos[13]),
                this.parseInteger(campos[14]),
                this.parseInteger(campos[15]),
                this.parseInteger(campos[16]),
                this.parseInteger(campos[17]),
                this.parseInteger(campos[18]));

    }

    private Integer parseInteger(String campo) {
        if (campo == null || campo.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.valueOf(campo);
        } catch (NumberFormatException e) {
            logger.warning("Campo inválido para conversão em Integer: " + campo + ". Valor padrão 0 será utilizado.");
            return 0;
        }
    }

}
