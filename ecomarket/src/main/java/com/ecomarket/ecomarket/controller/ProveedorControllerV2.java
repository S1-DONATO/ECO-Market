package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.ProveedorResourceAssembler;
import com.ecomarket.ecomarket.model.Proveedor;
import com.ecomarket.ecomarket.service.ProveedorService;
import com.ecomarket.ecomarket.resource.ProveedorResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/proveedores")
public class ProveedorControllerV2 {

    private final ProveedorService proveedorService;
    private final ProveedorResourceAssembler proveedorAssembler;

    public ProveedorControllerV2(ProveedorService proveedorService, ProveedorResourceAssembler proveedorAssembler) {
        this.proveedorService = proveedorService;
        this.proveedorAssembler = proveedorAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<ProveedorResource>>listar(){

        List<Proveedor> proveedores = proveedorService.listarTodosLosProveedores();

        if (proveedores.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ProveedorResource> proveedorResources = proveedores.stream()
                .map(proveedorAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<ProveedorResource> resources = CollectionModel.of(proveedorResources,
                linkTo(methodOn(ProveedorControllerV2.class).listar()).withSelfRel());

        return ResponseEntity.ok(resources);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ProveedorResource> guardar(@RequestBody Proveedor proveedor){
        Proveedor proveedorNuevo = proveedorService.crearProveedor(proveedor);
        ProveedorResource proveedorResource = proveedorAssembler.toModel(proveedorNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorResource);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ProveedorResource> buscar(@PathVariable Long id){
        try{
            Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
            ProveedorResource proveedorResource = proveedorAssembler.toModel(proveedor);
            return ResponseEntity.ok(proveedorResource);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ProveedorResource> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor){
        try{
            Proveedor proveedorActualizado = proveedorService.actualizarProveedor(id, proveedor);
            ProveedorResource provResource = proveedorAssembler.toModel(proveedorActualizado);
            return ResponseEntity.ok(provResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try{
            proveedorService.eliminarProveedor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }


}
