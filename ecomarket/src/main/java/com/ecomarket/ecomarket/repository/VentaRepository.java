package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {



}
