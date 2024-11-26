package com.arthurssrichard.safeworkmanager.dtos;

import java.time.LocalDate;
import java.util.List;

public class ExaminacaoDTO {



    private List<Double> resultadoNumerico;
    private List<String> resultadoBooleano;
    private List<Integer> idsNumericos;
    private List<Integer> idsBooleanos;


    private int idExame;
    public LocalDate dataRealizada;


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


    public LocalDate getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDate dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public List<Integer> getIdsNumericos() {
        return idsNumericos;
    }

    public void setIdsNumericos(List<Integer> idsNumericos) {
        this.idsNumericos = idsNumericos;
    }

    public List<Integer> getIdsBooleanos() {
        return idsBooleanos;
    }

    public void setIdsBooleanos(List<Integer> idsBooleanos) {
        this.idsBooleanos = idsBooleanos;
    }
}
