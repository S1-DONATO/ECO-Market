package com.ecomarket.ecomarket.repository;


import com.ecomarket.ecomarket.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findByIdProveedor(Long idProveedor);
    List<Proveedor> findByCorreo(String correo);

}