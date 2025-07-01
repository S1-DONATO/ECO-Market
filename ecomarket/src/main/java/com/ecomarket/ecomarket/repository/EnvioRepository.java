package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    List<Envio> findByEstado(String estado);
    List<Envio> findByfechaEnvio(Date fechaEnvio);
    List<Envio> findByFechaEntregaEstimada(Date fechaEntregaEstimada);
}
