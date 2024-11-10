package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id",nullable=false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "cargo_risco",
            joinColumns = @JoinColumn(name = "cargo_id"),
            inverseJoinColumns = @JoinColumn(name = "risco_id")
    )
    private Set<Risco> riscos = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "cargo_exame",
            joinColumns = @JoinColumn(name = "cargo_id"),
            inverseJoinColumns = @JoinColumn(name = "exame_id")
    )
    private Set<Exame> exames = new HashSet<>();


    @Column(nullable = false)
    private String nome;

    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<Risco> getRiscos() {
        return riscos;
    }

    public void setRiscos(Set<Risco> riscos) {
        this.riscos = riscos;
    }

    public Set<Exame> getExames() {
        return exames;
    }

    public void setExames(Set<Exame> exames) {
        this.exames = exames;
    }

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
}
