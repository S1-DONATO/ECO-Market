package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByNombre(Cliente nombre);
    List<Venta> findByDireccionEntrega(String direccionEntrega);
    List<Venta> findByFechaVenta(Date fechaVenta);

}
