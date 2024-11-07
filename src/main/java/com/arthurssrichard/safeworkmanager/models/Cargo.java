package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="empresa_id",nullable=false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "cargo_risco",
            joinColumns = @JoinColumn(name = "cargo_id"),
            inverseJoinColumns = @JoinColumn(name = "risco_id")
    )
    private Set<Risco> riscos = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "cargo_exame",
            joinColumns = @JoinColumn(name = "cargo_id"),
            inverseJoinColumns = @JoinColumn(name = "exame_id")
    )
    private Set<Exame> exames = new HashSet<>();


    @Column(nullable = false)
    private String nome;

    private String descricao;
}
