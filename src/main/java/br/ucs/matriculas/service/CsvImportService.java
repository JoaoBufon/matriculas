package br.ucs.matriculas.service;

import br.ucs.matriculas.dto.CamposCsvDTO;
import br.ucs.matriculas.entity.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CsvImportService {

    private final EstadoService estadoService;
    private final CidadeService cidadeService;
    private final InstituicaoEnsinoSuperiorService instituicaoEnsinoSuperiorService;
    private final CursoService cursoService;
    private final CursoIESService cursoIESService;
    private final CampusService campusService;
    private final MatriculasAnosCursosService matriculasAnosCursosService;
    Logger logger = Logger.getLogger(CsvImportService.class.getName());

    public CsvImportService(EstadoService estadoService, CidadeService cidadeService, InstituicaoEnsinoSuperiorService instituicaoEnsinoSuperiorService, CursoService cursoService, CursoIESService cursoIESService, CampusService campusService, MatriculasAnosCursosService matriculasAnosCursosService) {
        this.estadoService = estadoService;
        this.cidadeService = cidadeService;
        this.instituicaoEnsinoSuperiorService = instituicaoEnsinoSuperiorService;
        this.cursoService = cursoService;
        this.cursoIESService = cursoIESService;
        this.campusService = campusService;
        this.matriculasAnosCursosService = matriculasAnosCursosService;
    }

    public void importarCsv(String caminhoCsv, boolean possuiCabecalho, String separador) {
        List<CamposCsvDTO> listCsv = this.leituraCsv(caminhoCsv, possuiCabecalho, separador);

        List<Estado> listEstados = estadoService.saveAllInBatch(listCsv.stream().map(CamposCsvDTO::getDesEstado).distinct().map(Estado::new).toList());

        List<Cidade> listCidades = cidadeService.saveAllInBatch(listCsv.stream().map(dto -> new Cidade(
                dto.getDesCidade(),
                listEstados.stream().filter(estado -> Objects.equals(estado.getDesEstado(), dto.getDesEstado())).findFirst().orElse(null)
        )).distinct().toList());

        List<InstituicaoEnsinoSuperior> listIES = instituicaoEnsinoSuperiorService.saveAllInBatch(listCsv.stream()
                .map(dto -> new InstituicaoEnsinoSuperior(
                        dto.getDesIES(),
                        dto.getDesSigla(),
                        dto.getOrganizacao(),
                        dto.getCategoriaAdministrativa()
                ))
                .distinct()
                .toList());

        List<Curso> listCursos = cursoService.saveAllInBatch(listCsv.stream().map(dto -> new Curso(
                dto.getDesCurso(),
                dto.getDesDetalhadoCurso(),
                dto.getGrau()
        )).distinct().toList());

        List<CursoIES> listCursoIes = cursoIESService.saveAllInBatch(listCsv.stream().map(dto -> new CursoIES(
                listCursos.stream().filter(curso -> Objects.equals(dto.getDesCurso(), curso.getDesCurso())).findFirst().orElse(null),
                listIES.stream().filter(ies -> Objects.equals(dto.getDesIES(), ies.getDesIES())).findFirst().orElse(null),
                dto.getModalidade()

        )).distinct().toList());

        List<Campus> listCampus = campusService.saveAllCampusesInBatch(listCsv.stream().map(dto -> new Campus(
                listCidades.stream().filter(cidade -> Objects.equals(cidade.getDesCidade(), dto.getDesCidade())).findFirst().orElse(null),
                listIES.stream().filter(ies -> Objects.equals(dto.getDesIES(), ies.getDesIES())).findFirst().orElse(null)
        )).distinct().toList());

        /*List<MatriculasAnosCursos> listMatriculasAnosCursos = matriculasAnosCursosService.saveAllInBatch(listCsv.stream().map(dto -> new MatriculasAnosCursosService(
                listCursoIes.stream().filter(cursoIes -> Objects.equals(cursoI))
        )));*/
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
