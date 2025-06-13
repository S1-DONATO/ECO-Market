package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.ProductoControllerV2; // Asegúrate de que sea ProductoControllerV2 si ese es el que usas
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.resource.ProductoResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoResourceAssembler extends RepresentationModelAssemblerSupport<Producto, ProductoResource> {

    public ProductoResourceAssembler() {
        super(ProductoControllerV2.class, ProductoResource.class); // Aquí también debe ser ProductoControllerV2
    }

    @Override
    public ProductoResource toModel(Producto entity) {
        ProductoResource resource = instantiateModel(entity);

        resource.setIdProducto(entity.getIdProducto());
        resource.setNombre(entity.getNombre());
        resource.setDescripcion(entity.getDescripcion());
        resource.setPrecio(entity.getPrecio());
        resource.setStock(entity.getStock());
        resource.setDescuento(entity.getDescuento());
        resource.setCategoria(entity.getCategoria());

        resource.add(linkTo(methodOn(ProductoControllerV2.class).buscar(entity.getIdProducto())).withSelfRel()); // Usar buscar
        resource.add(linkTo(methodOn(ProductoControllerV2.class).eliminar(entity.getIdProducto())).withRel("eliminar")); // Usar eliminar
        resource.add(linkTo(methodOn(ProductoControllerV2.class).actualizar(entity.getIdProducto(), entity)).withRel("actualizar")); // Usar actualizar
        resource.add(linkTo(methodOn(ProductoControllerV2.class).listar()).withRel("productos")); // Usar listar

        return resource;
    }
}