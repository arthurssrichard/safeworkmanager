package com.arthurssrichard.safeworkmanager.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmpresaDTO {
    @NotBlank
    @NotNull
    private String nomeEmpresa;

    @NotBlank
    @NotNull
    private String cnpjEmpresa;

    @NotBlank
    @NotNull
    private String nomeAdministrador;

    @NotBlank
    @NotNull
    private String senhaAdministrador;


    public @NotBlank @NotNull String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(@NotBlank @NotNull String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public @NotBlank @NotNull String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(@NotBlank @NotNull String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public @NotBlank @NotNull String getNomeAdministrador() {
        return nomeAdministrador;
    }

    public void setNomeAdministrador(@NotBlank @NotNull String nomeAdministrador) {
        this.nomeAdministrador = nomeAdministrador;
    }

    public @NotBlank @NotNull String getSenhaAdministrador() {
        return senhaAdministrador;
    }

    public void setSenhaAdministrador(@NotBlank @NotNull String senhaAdministrador) {
        this.senhaAdministrador = senhaAdministrador;
    }
}
