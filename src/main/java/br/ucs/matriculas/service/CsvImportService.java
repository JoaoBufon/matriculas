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

    public void importarCsv(String caminhoCsv) {
        List<CamposCsvDTO> listCsv = this.leituraCsv(caminhoCsv);

        List<Estado> listEstados = this.saveEstados(listCsv);

        List<Cidade> listCidades = this.saveCidades(listCsv, listEstados);

        List<InstituicaoEnsinoSuperior> listIES = this.saveIes(listCsv);

        List<Curso> listCursos = this.saveCursos(listCsv);

        List<Campus> listCampus = this.saveCampuses(listCsv, listCidades, listIES);

        List<CursoIES> listCursoIes = this.saveCursosIes(listCsv,listCursos, listIES, listCampus);

    }

    private List<Campus> saveCampuses(List<CamposCsvDTO> listCsv, List<Cidade> listCidades, List<InstituicaoEnsinoSuperior> listIES) {

        Map<String, Cidade> mapCidades = listCidades.stream()
                .collect(Collectors.toMap(
                        Cidade::getDesCidade,
                        cidade -> cidade,
                        (valorAntigo, valorNovo) -> valorAntigo
                ));

        Map<String, InstituicaoEnsinoSuperior> mapIes = listIES.stream()
                .collect(Collectors.toMap(
                        InstituicaoEnsinoSuperior::getDesIES,
                        ies -> ies,
                        (valorAntigo, valorNovo) -> valorAntigo
                ));

        return campusService.saveAllCampusesInBatch(listCsv.stream().map(dto -> new Campus(
                mapCidades.get(dto.getDesCidade()),
                mapIes.get(dto.getDesIES())
        )).distinct().collect(Collectors.toList()));
    }

    private List<CursoIES> saveCursosIes(List<CamposCsvDTO> listCsv, List<Curso> listCursos, List<InstituicaoEnsinoSuperior> listIES, List<Campus> listCampus) {

        Map<String, Curso> mapCursos = listCursos.stream()
                .collect(Collectors.toMap(
                        curso -> curso.getDesCurso() + "#" + curso.getGrau(),
                        curso -> curso,
                        (valorAntigo, valorNovo) -> valorAntigo
                ));

        Map<String, InstituicaoEnsinoSuperior> mapIes = listIES.stream()
                .collect(Collectors.toMap(
                        InstituicaoEnsinoSuperior::getDesIES,
                        ies -> ies,
                        (valorAntigo, valorNovo) -> valorAntigo
                ));

        Map<String, Campus> mapCampus = listCampus.stream()
                .collect(Collectors.toMap(
                        campus -> campus.getCidade().getDesCidade() + "#" + campus.getInstituicaoEnsinoSuperior().getDesIES(),
                        campus -> campus,
                        (valorAntigo, valorNovo) -> valorAntigo
                ));

        Map<String, CursoIES> cursoIesMap = new HashMap<>();

        for (CamposCsvDTO dto : listCsv) {
            String cursoKey = dto.getDesCurso() + "#" + dto.getGrau();
            String iesKey = dto.getDesIES();
            String campusKey = dto.getDesCidade() + "#" + iesKey;

            Curso curso = mapCursos.get(cursoKey);
            InstituicaoEnsinoSuperior ies = mapIes.get(iesKey);
            Campus campus = mapCampus.get(campusKey);

            if (curso == null || ies == null || campus == null) {
                continue;
            }

            String compositeKey = cursoKey + "#" + dto.getModalidade() + "#" + iesKey + "#" + campusKey;

            CursoIES existente = cursoIesMap.get(compositeKey);
            if (existente == null) {
                cursoIesMap.put(compositeKey, new CursoIES(
                        curso,
                        ies,
                        dto.getModalidade(),
                        dto.getAno2014(),
                        dto.getAno2015(),
                        dto.getAno2016(),
                        dto.getAno2017(),
                        dto.getAno2018(),
                        dto.getAno2019(),
                        dto.getAno2020(),
                        dto.getAno2021(),
                        dto.getAno2022(),
                        campus
                ));
            } else {
                existente.setAno2014(existente.getAno2014() + dto.getAno2014());
                existente.setAno2015(existente.getAno2015() + dto.getAno2015());
                existente.setAno2016(existente.getAno2016() + dto.getAno2016());
                existente.setAno2017(existente.getAno2017() + dto.getAno2017());
                existente.setAno2018(existente.getAno2018() + dto.getAno2018());
                existente.setAno2019(existente.getAno2019() + dto.getAno2019());
                existente.setAno2020(existente.getAno2020() + dto.getAno2020());
                existente.setAno2021(existente.getAno2021() + dto.getAno2021());
                existente.setAno2022(existente.getAno2022() + dto.getAno2022());
            }
        }

        return cursoIESService.saveAllInBatch(new ArrayList<>(cursoIesMap.values()));
    }

    private List<Curso> saveCursos(List<CamposCsvDTO> listCsv) {
        return cursoService.saveAllInBatch(listCsv.stream().map(dto -> new Curso(
                dto.getDesCurso(),
                dto.getDesDetalhadoCurso(),
                dto.getGrau()
        )).distinct().collect(Collectors.toList()));
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
                .collect(Collectors.toList()));
    }

    private List<Cidade> saveCidades(List<CamposCsvDTO> listCsv, List<Estado> listEstados) {

        Map<String, Estado> mapEstados = listEstados.stream()
                .collect(Collectors.toMap(
                        Estado::getDesEstado,
                        estado -> estado,
                        (valorAntigo, valorNovo) -> valorAntigo
                ));

        return cidadeService.saveAllInBatch(listCsv.stream().map(dto -> new Cidade(
                dto.getDesCidade(),
                mapEstados.get(dto.getDesEstado())
        )).distinct().collect(Collectors.toList()));
    }

    private List<Estado> saveEstados(List<CamposCsvDTO> listCsv){
        return estadoService.saveAllInBatch(listCsv.stream().map(CamposCsvDTO::getDesEstado).distinct().map(Estado::new).collect(Collectors.toList()));
    }

    private List<CamposCsvDTO> leituraCsv(String caminhoCsv) {

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            String header = br.readLine();


            List<CamposCsvDTO> listLinhas = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";", -1);
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
