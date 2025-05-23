package com.ecomarket.ecomarket.controller;


import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecomarket.ecomarket.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/ECO-Market/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>>listar(){
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){
        try{
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        try{
            Cliente cli = clienteService.findById(id);
            cli.setIdCliente(id);
            cli.setApellido(cliente.getApellido());
            cli.setContrasena(cliente.getContrasena());
            cli.setCorreo(cliente.getCorreo());
            cli.setNombre(cliente.getNombre());
            cli.setDireccionEnvio(cliente.getDireccionEnvio());
            cli.setMetodoPagoPreferido(cliente.getMetodoPagoPreferido());
            cli.setNumeroTarjeta(cliente.getNumeroTarjeta());
            cli.setTelefono(cliente.getTelefono());

            clienteService.save(cli);
            return ResponseEntity.ok(cli);
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Long id){
        try{
            clienteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //??? que?
    //@GetMapping("{nombre}")
    //public ResponseEntity<List<Cliente>>buscarPorNombre(String nombre){
    //    List<Cliente> clienteNombre = clienteService.busquedaDeUsuario(nombre);
    //    if (clienteNombre.isEmpty()){
    //        return ResponseEntity.noContent().build();
    //    }
    //    return ResponseEntity.ok(clienteNombre);
    //}

}
