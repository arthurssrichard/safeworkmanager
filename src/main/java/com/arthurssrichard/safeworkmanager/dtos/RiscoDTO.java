package com.arthurssrichard.safeworkmanager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class RiscoDTO {
    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String observacao;

    @NotBlank
    @NotNull
    private String descricao;

    private List<String> agenteNome;
    private List<String> agenteDescricao;

    public @NotBlank @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotNull String getObservacao() {
        return observacao;
    }

    public void setObservacao(@NotBlank @NotNull String observacao) {
        this.observacao = observacao;
    }

    public @NotBlank @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @NotNull String descricao) {
        this.descricao = descricao;
    }

    public List<String> getAgenteNome() {
        return agenteNome;
    }

    public void setAgenteNome(List<String> agenteNome) {
        this.agenteNome = agenteNome;
    }

    public List<String> getAgenteDescricao() {
        return agenteDescricao;
    }

    public void setAgenteDescricao(List<String> agenteDescricao) {
        this.agenteDescricao = agenteDescricao;
    }
}
