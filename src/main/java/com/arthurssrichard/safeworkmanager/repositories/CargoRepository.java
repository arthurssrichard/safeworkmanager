package com.arthurssrichard.safeworkmanager.repositories;

import com.arthurssrichard.safeworkmanager.models.Cargo;
import com.arthurssrichard.safeworkmanager.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    public List<Cargo> findByEmpresa(Empresa empresa);
}
