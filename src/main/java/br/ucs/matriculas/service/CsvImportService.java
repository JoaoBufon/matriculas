package br.ucs.matriculas.service;

import br.ucs.matriculas.dto.CamposCsvDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvImportService {

    public void importarCsv(String caminhoCsv, boolean possuiCabecalho, String separador){
        this.leituraCsv(caminhoCsv, possuiCabecalho, separador);
    }

    private List<CamposCsvDTO> leituraCsv(String caminhoCsv, boolean possuiCabecalho, String separador) {

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCsv))) {
            if (possuiCabecalho) {
                String header = br.readLine();
            }

            List<CamposCsvDTO> listLinhas = new ArrayList<>();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(separador);

                listLinhas.add(this.parseCamposCsvDTO(campos));
            }

            return listLinhas;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private CamposCsvDTO parseCamposCsvDTO(String[] campos){
        return new CamposCsvDTO(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5],campos[6],campos[7],campos[8],campos[9],campos[10],Integer.valueOf(campos[11]),
                Integer.valueOf(campos[12]),Integer.valueOf(campos[13]),Integer.valueOf(campos[14]),
                Integer.valueOf(campos[15]),Integer.valueOf(campos[16]),Integer.valueOf(campos[17]),
                Integer.valueOf(campos[18]),Integer.valueOf(campos[19]));
    }
}
