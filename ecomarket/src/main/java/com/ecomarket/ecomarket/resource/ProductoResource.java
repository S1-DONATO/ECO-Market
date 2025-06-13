package com.ecomarket.ecomarket.resource;

import com.ecomarket.ecomarket.model.Producto;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation; // <--- ¡Esta es la importación CLAVE!
import lombok.Data;
import lombok.EqualsAndHashCode;

@Relation(collectionRelation = "productos", itemRelation = "producto")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductoResource extends RepresentationModel<ProductoResource> {

    private Long idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long stock;
    private Double descuento;
    private String categoria;

    public ProductoResource() {
    }

    public ProductoResource(Producto producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.stock = producto.getStock();
        this.descuento = producto.getDescuento();
        this.categoria = producto.getCategoria();
    }
}