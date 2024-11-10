package com.arthurssrichard.safeworkmanager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SetorDTO {

    @NotNull
    private int empresaId;

    @NotBlank
    @NotNull
    private String nome;

    private String descricao;

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public @NotBlank @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull String descricao) {
        this.descricao = descricao;
    }
}
