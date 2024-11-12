package com.arthurssrichard.safeworkmanager.dtos;

import com.arthurssrichard.safeworkmanager.models.TipoDado;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ExameDTO {

    @NotBlank
    private String nome;
    @NotNull
    private String descricao;
    @NotBlank
    private List<String> nomesDados;
    @NotBlank
    private List<TipoDado> tiposDados;

    private List<Double> resultadosValorMinimo;
    private List<Double> resultadosValorMaximo;
    private List <Boolean> resultadosBooleanos;


    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull String descricao) {
        this.descricao = descricao;
    }

    public @NotBlank List<String> getNomesDados() {
        return nomesDados;
    }

    public void setNomesDados(@NotBlank List<String> nomesDados) {
        this.nomesDados = nomesDados;
    }

    public @NotBlank List<TipoDado> getTiposDados() {
        return tiposDados;
    }

    public void setTiposDados(@NotBlank List<TipoDado> tiposDados) {
        this.tiposDados = tiposDados;
    }

    public List<Double> getResultadosValorMinimo() {
        return resultadosValorMinimo;
    }

    public void setResultadosValorMinimo(List<Double> resultadosValorMinimo) {
        this.resultadosValorMinimo = resultadosValorMinimo;
    }

    public List<Double> getResultadosValorMaximo() {
        return resultadosValorMaximo;
    }

    public void setResultadosValorMaximo(List<Double> resultadosValorMaximo) {
        this.resultadosValorMaximo = resultadosValorMaximo;
    }

    public List<Boolean> getResultadosBooleanos() {
        return resultadosBooleanos;
    }

    public void setResultadosBooleanos(List<Boolean> resultadosBooleanos) {
        this.resultadosBooleanos = resultadosBooleanos;
    }
}
