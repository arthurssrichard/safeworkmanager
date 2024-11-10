package com.arthurssrichard.safeworkmanager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class FuncionarioDTO {
    @NotBlank
    @NotNull
    private String nome;

    @NotNull
    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;

    @NotNull
    private int idSetor;

    @NotNull
    private int idCargo;

    public @NotBlank @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(@NotNull LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    @NotNull
    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(@NotNull int idSetor) {
        this.idSetor = idSetor;
    }

    @NotNull
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(@NotNull int idCargo) {
        this.idCargo = idCargo;
    }
}
