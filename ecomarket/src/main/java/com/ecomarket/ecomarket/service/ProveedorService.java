package com.ecomarket.ecomarket.service;

import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.model.Proveedor;
import com.ecomarket.ecomarket.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional

public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));
    }

    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizarProveedor(Long id, Proveedor proveedorActualizado) {
        return proveedorRepository.findById(id)
                .map(proveedorExistente ->{
                    proveedorExistente.setNombreProveedor(proveedorActualizado.getNombreProveedor());
                    proveedorExistente.setCorreo(proveedorActualizado.getCorreo());
                    proveedorExistente.setTelefonoProveedor(proveedorActualizado.getTelefonoProveedor());
                    proveedorExistente.setProductoSuministrado(proveedorActualizado.getProductoSuministrado());
                    return proveedorRepository.save(proveedorExistente);
                })
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id + " para actualizar."));
    }

    public void eliminarProveedor(Long id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Proveedor no encontrado con ID: " + id + " para eliminar.");
        }
    }

    public List<Proveedor> findByCorreo(String correo) {
        return proveedorRepository.findByCorreo(correo);
    }

    public List<Proveedor> findByTelefonoProveedor(String telefonoProveedor) {
        return proveedorRepository.findByTelefonoProveedor(telefonoProveedor);
    }

    public List<Proveedor> findByNombre(String nombreProveedor) {
        return proveedorRepository.findByNombre(nombreProveedor);
    }

    public List<Proveedor> findByProductoSuministrado(Producto producto) {
        return proveedorRepository.findByProductoSuministrado(producto);
    }

}