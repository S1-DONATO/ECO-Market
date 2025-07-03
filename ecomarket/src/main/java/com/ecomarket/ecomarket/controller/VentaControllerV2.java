package com.ecomarket.ecomarket.controller;


import com.ecomarket.ecomarket.assemblers.VentaResourceAssembler;
import com.ecomarket.ecomarket.model.Venta;
import com.ecomarket.ecomarket.resource.VentaResource;
import com.ecomarket.ecomarket.service.VentaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/ventas")
public class VentaControllerV2 {

    private final VentaService ventaService;
    private final VentaResourceAssembler ventaAssembler;

    public VentaControllerV2(VentaService ventaService, VentaResourceAssembler ventaAssembler) {
        this.ventaService = ventaService;
        this.ventaAssembler = ventaAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<VentaResource>> listar() {
        List<Venta> ventas = ventaService.listarTodasLasVentas();

        if (ventas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<VentaResource> ventaResources = ventas.stream()
                .map(ventaAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<VentaResource> resources = CollectionModel.of(ventaResources,
                linkTo(methodOn(VentaControllerV2.class).listar()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<VentaResource> guardar(@RequestBody Venta venta) {
        Venta ventaNueva = ventaService.crearVenta(venta);
        VentaResource ventaResource = ventaAssembler.toModel(ventaNueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaResource);
    }

    @GetMapping(value = "/{idVenta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<VentaResource> buscar(@PathVariable Long idVenta) {
        try{
            Venta venta = ventaService.obtenerVentaPorId(idVenta);
            VentaResource ventaResource = ventaAssembler.toModel(venta);
            return ResponseEntity.ok(ventaResource);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{idVenta}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<VentaResource> actualizar(@PathVariable Long idVenta, @RequestBody Venta venta) {
        try{
            Venta ventaActualizada = ventaService.actualizarVenta(idVenta, venta);
            VentaResource ventaResource = ventaAssembler.toModel(ventaActualizada);
            return ResponseEntity.ok(ventaResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idVenta}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idVenta) {
        try{
            ventaService.eliminarVenta(idVenta);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
