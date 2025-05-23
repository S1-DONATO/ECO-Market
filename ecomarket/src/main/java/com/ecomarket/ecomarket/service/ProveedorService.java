package com.ecomarket.ecomarket.service;


import com.ecomarket.ecomarket.model.Proveedor;
import com.ecomarket.ecomarket.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor findById(long id){
        return proveedorRepository.findById(id).get();
    }

    public Proveedor save(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    public void delete(Long id){
        proveedorRepository.deleteById(id);
    }

}
