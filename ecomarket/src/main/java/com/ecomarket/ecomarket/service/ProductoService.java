package com.ecomarket.ecomarket.service;

import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(Long id){
        return productoRepository.findById(id).get();
        //alternativa, talvez:
        //productoRepository.findByIdProducto(id)
        //                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    //talvez no necesario pero veo como seria util
    public List<Producto> findByCategoria(String categoria){
        return productoRepository.findByCategoria(categoria);
    }

    //talvez no necesario pero veo como seria util
    public Producto findByNombre(String nombre){
        return productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con Nombre: " + nombre));
    }

    //talvez no necesario pero veo como seria util
    public List<Producto> findByStock(Long stock){
        return productoRepository.findByStock( stock );
    }

    //talvez no necesario y no veo como seria util
    public List<Producto> findByPrecio(double precio){
        return productoRepository.findByPrecio(precio);
    }

    //talvez no necesario y no veo como seria util
    public List<Producto> findByDescuento(double descuento){
        return productoRepository.findByDescuento(descuento);
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }

}
