package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    List<Envio> findByIdEnvio(Long idEnvio);
    List<Envio> findByEstado(String estado);
    List<Envio> findByfechaEnvio(Date fechaEnvio);
    List<Envio> findByFechaEntregaEstimada(Date fechaEntregaEstimada);
    List<Envio> findByDestino(String destino);
    List<Envio> findByVenta(Venta venta);
}
