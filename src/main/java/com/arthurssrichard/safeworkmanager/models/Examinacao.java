package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Examinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name="exame_id")
    private Exame exame;

    private LocalDate dataRealizada;

    @OneToMany(mappedBy = "examinacao", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ItemExaminacao> itemExaminacaoList = new ArrayList<>();

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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public LocalDate getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(LocalDate dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public List<ItemExaminacao> getItemExaminacaoList() {
        return itemExaminacaoList;
    }

    public void setItemExaminacaoList(List<ItemExaminacao> itemExaminacaoList) {
        this.itemExaminacaoList = itemExaminacaoList;
    }
}
