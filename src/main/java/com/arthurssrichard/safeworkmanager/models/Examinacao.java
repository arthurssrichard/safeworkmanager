package com.arthurssrichard.safeworkmanager.models;

import jakarta.persistence.*;

import java.time.LocalDate;

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
}
