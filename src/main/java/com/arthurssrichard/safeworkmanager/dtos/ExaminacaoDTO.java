package com.arthurssrichard.safeworkmanager.dtos;

import java.time.LocalDate;
import java.util.List;

public class ExaminacaoDTO {


    private List<String> nomesDados;
    private List<Double> resultadoNumerico;
    private List<String> resultadoBooleano;
    private List<String> nomesNumericos;
    private List<String> nomesBooleanos;
    private int idExame;
    public LocalDate dataRealizada;


    public List<String> getNomesDados() {
        return nomesDados;
    }

    public void setNomesDados(List<String> nomesDados) {
        this.nomesDados = nomesDados;
    }

    public List<Double> getResultadoNumerico() {
        return resultadoNumerico;
    }

    public void setResultadoNumerico(List<Double> resultadoNumerico) {
        this.resultadoNumerico = resultadoNumerico;
    }

    public List<String> getResultadoBooleano() {
        return resultadoBooleano;
    }

    public void setResultadoBooleano(List<String> resultadoBooleano) {
        this.resultadoBooleano = resultadoBooleano;
    }

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }

    public List<String> getNomesNumericos() {
        return nomesNumericos;
    }

    public void setNomesNumericos(List<String> nomesNumericos) {
        this.nomesNumericos = nomesNumericos;
    }

    public List<String> getNomesBooleanos() {
        return nomesBooleanos;
    }

    public void setNomesBooleanos(List<String> nomesBooleanos) {
        this.nomesBooleanos = nomesBooleanos;
    }

    public LocalDate getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDate dataRealizada) {
        this.dataRealizada = dataRealizada;
    }
}
