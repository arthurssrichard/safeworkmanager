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

    private Double resultadoNumerico;
    private Double resultadoBooleano;
}
