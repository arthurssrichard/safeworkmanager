package com.arthurssrichard.safeworkmanager.repositories;

import com.arthurssrichard.safeworkmanager.models.Empresa;
import com.arthurssrichard.safeworkmanager.models.Risco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiscoRepository extends JpaRepository<Risco, Integer> {
    public List<Risco> findByEmpresa(Empresa empresa);
}
