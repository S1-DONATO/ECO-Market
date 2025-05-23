package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByIdVenta(Long idVenta);
    List<Venta> findByEstadoVenta(String estadoVenta);
    List<Venta> findByfechaVenta(Date fechaVenta);
}
