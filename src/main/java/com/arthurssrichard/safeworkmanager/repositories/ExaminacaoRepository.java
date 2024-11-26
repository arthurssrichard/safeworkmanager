package com.arthurssrichard.safeworkmanager.repositories;

import com.arthurssrichard.safeworkmanager.models.Examinacao;
import com.arthurssrichard.safeworkmanager.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminacaoRepository extends JpaRepository<Examinacao, Integer> {
}
