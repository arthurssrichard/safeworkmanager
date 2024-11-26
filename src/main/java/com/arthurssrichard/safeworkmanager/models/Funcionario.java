package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="setor_id",nullable = false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name="cargo_id",nullable = false)
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name="empresa_id",nullable=false)
    private Empresa empresa;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Examinacao> examinacoes = new ArrayList<>();

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public List<Examinacao> getExaminacoes() {
        return examinacoes;
    }

    public void setExaminacoes(List<Examinacao> examinacoes) {
        this.examinacoes = examinacoes;
    }
}
