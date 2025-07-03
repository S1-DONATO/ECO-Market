package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.resource.ProductoResource;
import com.ecomarket.ecomarket.assemblers.ProductoResourceAssembler;
import com.ecomarket.ecomarket.service.ProductoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ECO-Market/v1/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoResourceAssembler assembler;

    public ProductoController(ProductoService productoService, ProductoResourceAssembler assembler) {
        this.productoService = productoService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResource> obtenerProducto(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResource> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Producto updatedProducto = productoService.actualizarProducto(id, productoActualizado);
        if (updatedProducto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(assembler.toModel(updatedProducto));
    }

    @GetMapping
    public CollectionModel<ProductoResource> listarProductos() {
        List<Producto> productos = productoService.listarTodosLosProductos();
        List<ProductoResource> resources = productos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(resources,
                linkTo(methodOn(ProductoController.class).listarProductos()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<ProductoResource> crearProducto(@RequestBody Producto nuevoProducto) {
        Producto productoGuardado = productoService.crearProducto(nuevoProducto);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoController.class).obtenerProducto(productoGuardado.getIdProducto())).toUri())
                .body(assembler.toModel(productoGuardado));
    }
}