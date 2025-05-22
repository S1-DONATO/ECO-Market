package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByIdProducto(int idProducto);
    Optional<Producto> findByNombre(String nombre);
    List<Producto> findByPrecio(double precio);
    List<Producto> findByStock(int stock);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByDescuento(double descuento);

}
