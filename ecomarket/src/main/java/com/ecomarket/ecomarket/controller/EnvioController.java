package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ECO-Market/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listar(){
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @PostMapping
    public ResponseEntity<Envio> guardar(@RequestBody Envio envio){
        Envio envioNuevo = envioService.save(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscar(@PathVariable Long id){
        try{
            Envio envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @RequestBody Envio envio){
        try{
            Envio env = envioService.findById(id);
            env.setIdEnvio(id);
            env.setDestino(envio.getDestino());
            env.setFechaEnvio(envio.getFechaEnvio());
            env.setEstado(envio.getEstado());
            env.setOrigen(envio.getOrigen());
            env.setDireccionEntrega(envio.getDireccionEntrega());
            env.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());
            env.setVenta(envio.getVenta());

            envioService.save(env);
            return ResponseEntity.ok(env);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Envio> eliminar(@PathVariable Long id){
        try{
            envioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
