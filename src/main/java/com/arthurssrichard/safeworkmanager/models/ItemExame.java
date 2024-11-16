package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;

@Entity
public class ItemExame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="exame_id",nullable = false)
    private Exame exame;

    @Column(nullable = false)
    private String nomeDado; // Ex, "Hemoglobina (g/Dl)" ou para resultado booleano, "Manchas aparentes no abdômem?"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDado tipoDado;

    private Double resultadoValorMinimo;
    private Double resultadoValorMaximo;
    private boolean resultadoBooleano;


    public ItemExame() {}

    public ItemExame(Empresa empresa, Exame exame, String nomeDado, TipoDado tipoDado, boolean resultadoBooleano) {
        this.empresa = empresa;
        this.exame = exame;
        this.nomeDado = nomeDado;
        this.tipoDado = tipoDado;
        this.resultadoBooleano = resultadoBooleano;
    }

    public ItemExame(Empresa empresa, Exame exame, String nomeDado, TipoDado tipoDado, Double resultadoValorMinimo, Double resultadoValorMaximo) {
        this.empresa = empresa;
        this.exame = exame;
        this.nomeDado = nomeDado;
        this.tipoDado = tipoDado;
        this.resultadoValorMinimo = resultadoValorMinimo;
        this.resultadoValorMaximo = resultadoValorMaximo;
    }

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

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public String getNomeDado() {
        return nomeDado;
    }

    public void setNomeDado(String nomeDado) {
        this.nomeDado = nomeDado;
    }

    public TipoDado getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(TipoDado tipoDado) {
        this.tipoDado = tipoDado;
    }

    public Double getResultadoValorMinimo() {
        return resultadoValorMinimo;
    }

    public void setResultadoValorMinimo(Double resultadoValorMinimo) {
        this.resultadoValorMinimo = resultadoValorMinimo;
    }

    public Double getResultadoValorMaximo() {
        return resultadoValorMaximo;
    }

    public void setResultadoValorMaximo(Double resultadoValorMaximo) {
        this.resultadoValorMaximo = resultadoValorMaximo;
    }

    public boolean isResultadoBooleano() {
        return resultadoBooleano;
    }

    public void setResultadoBooleano(boolean resultadoBooleano) {
        this.resultadoBooleano = resultadoBooleano;
    }
}
