package com.ecomarket.ecomarket.service;

import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomarket.ecomarket.repository.VentaRepository;

import java.util.List;

@Service
@Transactional

public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findall(){
        return ventaRepository.findAll();
    }

    public Venta findById(long id){
        return ventaRepository.findById(id).get();
    }

    public Venta save(Venta venta){
        return ventaRepository.save(venta);
    }

    public void delete(Long id){
        ventaRepository.deleteById(id);
    }

}
