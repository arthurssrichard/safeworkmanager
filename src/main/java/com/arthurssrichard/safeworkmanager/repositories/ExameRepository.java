package com.arthurssrichard.safeworkmanager.repositories;

import com.arthurssrichard.safeworkmanager.models.Cargo;
import com.arthurssrichard.safeworkmanager.models.Empresa;
import com.arthurssrichard.safeworkmanager.models.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExameRepository extends JpaRepository<Exame, Integer> {
    @Query("SELECT e FROM Exame e JOIN e.cargos c WHERE c = :cargo")
    public List<Exame> findByCargo(@Param("cargo") Cargo cargo);

    public List<Exame> findByEmpresa(Empresa empresa);
}
