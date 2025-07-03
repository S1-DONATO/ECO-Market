package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.model.Proveedor;
import com.ecomarket.ecomarket.resource.ProveedorResource;
import com.ecomarket.ecomarket.assemblers.ProveedorResourceAssembler;
import com.ecomarket.ecomarket.service.ProveedorService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ECO-Market/v1/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final ProveedorResourceAssembler proveedorAssembler;

    public ProveedorController(ProveedorService proveedorService, ProveedorResourceAssembler proveedorAssembler) {
        this.proveedorService = proveedorService;
        this.proveedorAssembler = proveedorAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResource> obtenerProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        if (proveedor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedorAssembler.toModel(proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResource> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorActualizado) {
        Proveedor updatedProveedor = proveedorService.actualizarProveedor(id, proveedorActualizado);
        if (updatedProveedor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedorAssembler.toModel(updatedProveedor));
    }

    @GetMapping
    public CollectionModel<ProveedorResource> listarProveedores() {
        List<Proveedor> productos = proveedorService.listarTodosLosProveedores();
        List<ProveedorResource> resources = productos.stream()
                .map(proveedorAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(resources,
                linkTo(methodOn(ProveedorController.class).listarProveedores()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<ProveedorResource> crearProveedor(@RequestBody Proveedor nuevoProveedor) {
        Proveedor proveedorGuardado = proveedorService.crearProveedor(nuevoProveedor);
        return ResponseEntity
                .created(linkTo(methodOn(ProveedorController.class).obtenerProveedor(proveedorGuardado.getIdProveedor())).toUri())
                .body(proveedorAssembler.toModel(proveedorGuardado));
    }

}
