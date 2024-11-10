package com.arthurssrichard.safeworkmanager.dtos;

import com.arthurssrichard.safeworkmanager.models.Risco;
import com.arthurssrichard.safeworkmanager.repositories.RiscoRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CargoDTO {

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    private String descricao;

    private List<Integer> riscos;

    public @NotBlank @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotNull String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank @NotNull String descricao) {
        this.descricao = descricao;
    }

    public List<Integer> getRiscos() {
        return riscos;
    }

    public void setRiscos(List<Integer> riscos) {
        this.riscos = riscos;
    }
}
