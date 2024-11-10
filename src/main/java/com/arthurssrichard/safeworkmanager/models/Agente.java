package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

@Entity
public class Agente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="risco_id", nullable=false)
    private Risco risco;

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

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
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
