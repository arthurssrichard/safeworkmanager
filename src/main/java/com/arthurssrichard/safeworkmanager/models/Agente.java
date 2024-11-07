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
}
