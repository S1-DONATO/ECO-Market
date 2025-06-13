package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.ProductoResourceAssembler;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.service.ProductoService;
import com.ecomarket.ecomarket.resource.ProductoResource;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ECO-Market/v2/productos")
public class ProductoControllerV2 {

    private final ProductoService productoService;
    private final ProductoResourceAssembler productoAssembler;

    public ProductoControllerV2(ProductoService productoService, ProductoResourceAssembler productoAssembler) {
        this.productoService = productoService;
        this.productoAssembler = productoAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<ProductoResource>> listar(){
        List<Producto> productos = productoService.listarTodosLosProductos(); // Nombre del método corregido

        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ProductoResource> productoResources = productos.stream()
                .map(productoAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<ProductoResource> resources = CollectionModel.of(productoResources,
                linkTo(methodOn(ProductoControllerV2.class).listar()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ProductoResource> guardar(@RequestBody Producto producto){
        Producto productoNuevo = productoService.crearProducto(producto); // Nombre del método corregido
        ProductoResource productoResource = productoAssembler.toModel(productoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoResource);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ProductoResource> buscar(@PathVariable Long id){
        try{
            Producto producto = productoService.obtenerProductoPorId(id); // Nombre del método corregido
            ProductoResource productoResource = productoAssembler.toModel(producto);
            return ResponseEntity.ok(productoResource);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ProductoResource> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        try{
            Producto productoActualizado = productoService.actualizarProducto(id, producto); // Nombre del método corregido
            ProductoResource proResource = productoAssembler.toModel(productoActualizado);
            return ResponseEntity.ok(proResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try{
            productoService.eliminarProducto(id); // Nombre del método corregido
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}