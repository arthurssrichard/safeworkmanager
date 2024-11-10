package com.arthurssrichard.safeworkmanager.repositories;

import com.arthurssrichard.safeworkmanager.models.Empresa;
import com.arthurssrichard.safeworkmanager.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    public List<Funcionario> findByEmpresa(Empresa empresa);
}
