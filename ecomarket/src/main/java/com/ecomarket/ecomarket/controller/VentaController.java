package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.model.Venta;
import com.ecomarket.ecomarket.resource.VentaResource;
import com.ecomarket.ecomarket.assemblers.VentaResourceAssembler;
import com.ecomarket.ecomarket.service.VentaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ECO-Market/v1/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final VentaResourceAssembler ventaAssembler;

    public VentaController(VentaService ventaService, VentaResourceAssembler ventaAssembler) {
        this.ventaService = ventaService;
        this.ventaAssembler = ventaAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResource> obtenerVenta(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        if (venta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ventaAssembler.toModel(venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResource> actualizarVenta(@PathVariable Long id, @RequestBody Venta ventaActualizada) {
        Venta updatedVenta = ventaService.actualizarVenta(id, ventaActualizada);
        if (updatedVenta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ventaAssembler.toModel(updatedVenta));
    }

    @GetMapping
    public CollectionModel<VentaResource> listarVentas() {
        List<Venta> ventas = ventaService.listarTodasLasVentas();
        List<VentaResource> resources = ventas.stream()
                .map(ventaAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(resources,
                linkTo(methodOn(VentaController.class).listarVentas()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<VentaResource> crearVenta(@RequestBody Venta nuevaVenta) {
        Venta ventaGuardada = ventaService.crearVenta(nuevaVenta);
        return ResponseEntity
                .created(linkTo(methodOn(VentaController.class).obtenerVenta(ventaGuardada.getIdVenta())).toUri())
                .body(ventaAssembler.toModel(ventaGuardada));
    }

}
