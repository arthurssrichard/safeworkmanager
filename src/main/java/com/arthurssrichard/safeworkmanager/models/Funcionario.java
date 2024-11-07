package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;
import java.time.LocalDate;

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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;
}
