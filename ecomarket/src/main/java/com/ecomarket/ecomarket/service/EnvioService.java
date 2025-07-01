package com.ecomarket.ecomarket.service;

import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.repository.EnvioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnvioService {

    private final EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> listarTodosLosEnvios() {
        return envioRepository.findAll();
    }

    public Envio obtenerEnvioPorId(Long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado con ID: " + id));
    }

    public Envio crearEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio actualizarEnvio(Long id, Envio envioActualizado) {
        return envioRepository.findById(id)
                .map(envioExistente ->{
                    envioExistente.setFechaEnvio(envioActualizado.getFechaEnvio());
                    envioExistente.setVenta(envioActualizado.getVenta());
                    envioExistente.setOrigen(envioActualizado.getOrigen());
                    envioExistente.setDestino(envioActualizado.getDestino());
                    envioExistente.setDireccionEntrega(envioActualizado.getDireccionEntrega());
                    envioExistente.setFechaEntregaEstimada(envioActualizado.getFechaEntregaEstimada());
                    envioExistente.setEstado(envioActualizado.getEstado());
                    return envioRepository.save(envioExistente);
                })
                .orElseThrow(()-> new RuntimeException("Envio no encontrado con ID: " +id+ " para actualizar."));
    }

    public void eliminarEnvio(Long id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Envio no encontrado con ID: " + id + " para eliminar.");
        }
    }

    public List<Envio> findByEstado(String estado){
        return envioRepository.findByEstado(estado);
    }

    public List<Envio> findByfechaEnvio(Date fechaEnvio){
        return envioRepository.findByfechaEnvio(fechaEnvio);
    }

    public List<Envio> findByFechaEntregaEstimada(Date fechaEntregaEstimada){
        return envioRepository.findByFechaEntregaEstimada(fechaEntregaEstimada);
    }

}
