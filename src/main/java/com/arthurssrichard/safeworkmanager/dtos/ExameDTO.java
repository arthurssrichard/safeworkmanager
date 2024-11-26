package com.arthurssrichard.safeworkmanager.dtos;

import com.arthurssrichard.safeworkmanager.models.Cargo;
import com.arthurssrichard.safeworkmanager.models.ItemExame;
import com.arthurssrichard.safeworkmanager.models.TipoDado;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public class ExameDTO {
    private int id;
    private String nome;
    private String descricao;
    private List<String> nomeDadoNumerico;
    private List<Double> minimoEsperado;
    private List<Double> maximoEsperado;

    private List<String> nomeDadoBooleano;
    private List<String> resultadoBooleanoEsperado;
    private List<Integer> cargos;
    private Set<Cargo> cargosEdit;
    private Set<ItemExame> itensExameEdit;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getNomeDadoNumerico() {
        return nomeDadoNumerico;
    }

    public void setNomeDadoNumerico(List<String> nomeDadoNumerico) {
        this.nomeDadoNumerico = nomeDadoNumerico;
    }

    public List<Double> getMinimoEsperado() {
        return minimoEsperado;
    }

    public void setMinimoEsperado(List<Double> minimoEsperado) {
        this.minimoEsperado = minimoEsperado;
    }

    public List<Double> getMaximoEsperado() {
        return maximoEsperado;
    }

    public void setMaximoEsperado(List<Double> maximoEsperado) {
        this.maximoEsperado = maximoEsperado;
    }

    public List<String> getNomeDadoBooleano() {
        return nomeDadoBooleano;
    }

    public void setNomeDadoBooleano(List<String> nomeDadoBooleano) {
        this.nomeDadoBooleano = nomeDadoBooleano;
    }

    public List<String> getResultadoBooleanoEsperado() {
        return resultadoBooleanoEsperado;
    }

    public void setResultadoBooleanoEsperado(List<String> resultadoBooleanoEsperado) {
        this.resultadoBooleanoEsperado = resultadoBooleanoEsperado;
    }

    public List<Integer> getCargos() {
        return cargos;
    }

    public void setCargos(List<Integer> cargos) {
        this.cargos = cargos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Cargo> getCargosEdit() {
        return cargosEdit;
    }

    public void setCargosEdit(Set<Cargo> cargosEdit) {
        this.cargosEdit = cargosEdit;
    }

    public Set<ItemExame> getItensExameEdit() {
        return itensExameEdit;
    }

    public void setItensExameEdit(Set<ItemExame> itensExameEdit) {
        this.itensExameEdit = itensExameEdit;
    }
}
