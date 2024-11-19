package com.arthurssrichard.safeworkmanager.dtos;

import com.arthurssrichard.safeworkmanager.models.NivelAcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {
    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String senha;
    @NotNull
    private NivelAcesso nivelAcesso;

    public @NotBlank @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotNull String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank @NotNull String senha) {
        this.senha = senha;
    }

    public @NotBlank @NotNull NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(@NotBlank @NotNull NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
}
