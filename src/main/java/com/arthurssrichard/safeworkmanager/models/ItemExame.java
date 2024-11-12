package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

@Entity
public class ItemExame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable = false)
    Empresa empresa;

    @ManyToOne
    @JoinColumn(name="exame_id",nullable = false)
    Exame exame;

    @Column(nullable = false)
    private String nomeDado; // Ex, "Hemoglobina (g/Dl)" ou para resultado booleano, "Manchas aparentes no abdômem?"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDado tipoDado;

    private Double resultadoValorMinimo;
    private Double resultadoValorMaximo;
    private boolean resultadoBooleano;
}
