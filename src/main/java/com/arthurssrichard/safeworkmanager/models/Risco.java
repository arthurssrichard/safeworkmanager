package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Risco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private Empresa empresa;

    @ManyToMany(mappedBy = "riscos")
    private Set<Cargo> cargos = new HashSet<>();

    @Column(nullable = false)
    private String nome;

    private String descricao;
    private String observacao;

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

    public Set<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(Set<Cargo> cargos) {
        this.cargos = cargos;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
