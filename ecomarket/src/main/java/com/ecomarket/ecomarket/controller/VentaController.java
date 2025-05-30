package com.ecomarket.ecomarket.controller;


import com.ecomarket.ecomarket.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.ecomarket.ecomarket.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/ECO-Market/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> ventas = ventaService.findall();
        if (ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta){
        Venta ventaNueva = ventaService.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscar(@PathVariable Long id){
        try{
            Venta venta = ventaService.findById(id);
            return ResponseEntity.ok(venta);
        } catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta){
        try{
            Venta ven = ventaService.findById(id);
            ven.setIdVenta(id);
            ven.setEstadoVenta(venta.getEstadoVenta());
            ven.setFechaVenta(venta.getFechaVenta());
            ven.setNombre(venta.getNombre());
            ven.setDireccionEntrega(venta.getDireccionEntrega());
            ven.setMetodoPago(venta.getMetodoPago());
            ven.setProductos(venta.getProductos());

            ventaService.save(ven);
            return ResponseEntity.ok(venta);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            ventaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
