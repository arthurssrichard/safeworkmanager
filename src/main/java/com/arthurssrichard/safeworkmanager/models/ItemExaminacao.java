package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

@Entity
public class ItemExaminacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="examinacao_id")
    private Examinacao examinacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDado tipoDado;

    private String nomeDado;

    private Double resultadoNumerico;
    private Boolean resultadoBooleano;

    @ManyToOne
    @JoinColumn(name="item_exame_id")
    private ItemExame itemExame;
    

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

    public Examinacao getExaminacao() {
        return examinacao;
    }

    public void setExaminacao(Examinacao examinacao) {
        this.examinacao = examinacao;
    }

    public TipoDado getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(TipoDado tipoDado) {
        this.tipoDado = tipoDado;
    }

    public String getNomeDado() {
        return nomeDado;
    }

    public void setNomeDado(String nomeDado) {
        this.nomeDado = nomeDado;
    }

    public Double getResultadoNumerico() {
        return resultadoNumerico;
    }

    public void setResultadoNumerico(Double resultadoNumerico) {
        this.resultadoNumerico = resultadoNumerico;
    }

    public Boolean getResultadoBooleano() {
        return resultadoBooleano;
    }

    public void setResultadoBooleano(Boolean resultadoBooleano) {
        this.resultadoBooleano = resultadoBooleano;
    }

    public ItemExame getItemExame() {
        return itemExame;
    }

    public void setItemExame(ItemExame itemExame) {
        this.itemExame = itemExame;
    }
}
