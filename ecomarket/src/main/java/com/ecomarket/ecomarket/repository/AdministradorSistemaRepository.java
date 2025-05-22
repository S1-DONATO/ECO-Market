package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.AdministradorSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorSistemaRepository extends JpaRepository<AdministradorSistema, Long> {

    List<AdministradorSistema> findById(Integer idAdministradorSistema);

}
