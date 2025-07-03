package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.ClienteResourceAssembler;
import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.resource.ClienteResource;
import com.ecomarket.ecomarket.service.ClienteService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteControllerV2 {

    private final ClienteService clienteService;
    private final ClienteResourceAssembler clienteAssembler;

    public ClienteControllerV2(ClienteService clienteService, ClienteResourceAssembler clienteAssembler) {
        this.clienteService = clienteService;
        this.clienteAssembler = clienteAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<ClienteResource>> listar(){
        List<Cliente> clientes = clienteService.listarTodosLosClientes(); // Nombre del método corregido

        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ClienteResource> clienteResources = clientes.stream()
                .map(clienteAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<ClienteResource> resources = CollectionModel.of(clienteResources,
                linkTo(methodOn(ClienteControllerV2.class).listar()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ClienteResource> guardar(@RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.crearCliente(cliente); // Nombre del método corregido
        ClienteResource clienteResource = clienteAssembler.toModel(clienteNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResource);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ClienteResource> buscar(@PathVariable Long id){
        try{
            Cliente cliente = clienteService.obtenerClientePorId(id); // Nombre del método corregido
            ClienteResource clienteResource = clienteAssembler.toModel(cliente);
            return ResponseEntity.ok(clienteResource);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<ClienteResource> actualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        try{
            Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente); // Nombre del método corregido
            ClienteResource clienteResource = clienteAssembler.toModel(clienteActualizado);
            return ResponseEntity.ok(clienteResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try{
            clienteService.eliminarCliente(id); // Nombre del método corregido
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

}
