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

    // Obtener todos los productos
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    //Encontrar producto por ID
    public Producto findById(Long id){
        return productoRepository.findByIdProducto(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    //Buscar producto por nombre
    public Producto findByNombre(String nombre){
        return productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con Nombre: " + nombre));
    }

    //Buscar producto por precios
    public List<Producto> findByPrecio(double precio){
        return productoRepository.findByPrecio(precio);
    }

    //Buscar por stock
    public List<Producto> findByStock(Long stock){
        return productoRepository.findByStock( stock );
    }

    //Buscar por categoria
    public List<Producto> findByCategoria(String categoria){
        return productoRepository.findByCategoria(categoria);
    }

    //Buscar por descuento
    public List<Producto> findByDescuento(double descuento){
        return productoRepository.findByDescuento(descuento);
    }

    //Guardar un nuevo producto
    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }
}
