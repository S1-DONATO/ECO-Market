package com.ecomarket.ecomarket.service;

import com.ecomarket.ecomarket.model.Cliente;
import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Venta;
import org.springframework.stereotype.Service;
import com.ecomarket.ecomarket.repository.VentaRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listarTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerVentaPorId(Long idVenta) {
        return ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: "+idVenta));
    }

    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(Long idVenta, Venta ventaActualizada) {
        return ventaRepository.findById(idVenta)
                .map(ventaExistente -> {
                    ventaExistente.setNombre(ventaActualizada.getNombre());
                    ventaExistente.setProductos(ventaActualizada.getProductos());
                    ventaExistente.setEstadoVenta(ventaActualizada.getEstadoVenta());
                    ventaExistente.setMetodoPago(ventaActualizada.getMetodoPago());
                    ventaExistente.setDireccionEntrega(ventaActualizada.getDireccionEntrega());
                    ventaExistente.setFechaVenta(ventaActualizada.getFechaVenta());
                    return ventaRepository.save(ventaExistente);
        })
        .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " +idVenta+ " para actualizar."));
    }

    public void eliminarVenta(Long idVenta) {
        if (ventaRepository.existsById(idVenta)) {
            ventaRepository.deleteById(idVenta);
        } else {
            throw new RuntimeException("Venta no encontrada con ID: "+idVenta+" para eliminar.");
        }
    }

    public List<Venta> findByNombre(Cliente nombre){
        return ventaRepository.findByNombre(nombre);
    }

    public List<Venta> findByDireccionEntrega(String direccionEntrega){
        return ventaRepository.findByDireccionEntrega(direccionEntrega);
    }

    public List<Venta> findByFechaVenta(Date fechaVenta){
        return ventaRepository.findByFechaVenta(fechaVenta);
    }

}
