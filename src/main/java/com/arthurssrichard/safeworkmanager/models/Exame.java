package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id",nullable=false)
    private Empresa empresa;

    @ManyToMany(mappedBy = "exames")
    private Set<Cargo> cargos = new HashSet<>();

    @Column(nullable = false)
    private String nome;

    private String descricao;
}
