package com.arthurssrichard.safeworkmanager.repositories;

import com.arthurssrichard.safeworkmanager.models.Empresa;
import com.arthurssrichard.safeworkmanager.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SetorRepository extends JpaRepository<Setor, Integer> {
    List<Setor> findByEmpresa(Empresa empresa);
}
