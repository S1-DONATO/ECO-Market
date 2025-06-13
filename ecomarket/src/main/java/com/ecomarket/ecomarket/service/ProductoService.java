package com.ecomarket.ecomarket.service;

import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodosLosProductos(){ // Nombre corregido
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id){ // Nombre corregido
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto crearProducto(Producto producto){ // Nombre corregido
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) { // Nombre corregido
        return productoRepository.findById(id)
                .map(productoExistente -> {
                    productoExistente.setCategoria(productoActualizado.getCategoria());
                    productoExistente.setNombre(productoActualizado.getNombre());
                    productoExistente.setDescripcion(productoActualizado.getDescripcion());
                    productoExistente.setDescuento(productoActualizado.getDescuento());
                    productoExistente.setStock(productoActualizado.getStock());
                    productoExistente.setPrecio(productoActualizado.getPrecio());
                    return productoRepository.save(productoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id + " para actualizar."));
    }

    public void eliminarProducto(Long id){ // Nombre corregido
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id + " para eliminar.");
        }
    }

    public List<Producto> findByCategoria(String categoria){
        return productoRepository.findByCategoria(categoria);
    }

    public Producto findByNombre(String nombre){
        return productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con Nombre: " + nombre));
    }

    public List<Producto> findByStock(Long stock){
        return productoRepository.findByStock(stock);
    }

    public List<Producto> findByPrecio(double precio){
        return productoRepository.findByPrecio(precio);
    }

    public List<Producto> findByDescuento(double descuento){
        return productoRepository.findByDescuento(descuento);
    }
}