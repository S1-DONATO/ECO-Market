package com.ecomarket.ecomarket.controller;


import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.resource.ClienteResource;
import com.ecomarket.ecomarket.assemblers.ClienteResourceAssembler;
import com.ecomarket.ecomarket.service.ClienteService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ECO-Market/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteResourceAssembler clienteAssembler;

    public ClienteController(ClienteService clienteService, ClienteResourceAssembler clienteAssembler) {
        this.clienteService = clienteService;
        this.clienteAssembler = clienteAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResource> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteAssembler.toModel(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResource> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Cliente updatedCliente = clienteService.actualizarCliente(id, clienteActualizado);
        if (updatedCliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteAssembler.toModel(updatedCliente));
    }

    @GetMapping
    public CollectionModel<ClienteResource> listarClientes() {
        List<Cliente> clientes = clienteService.listarTodosLosClientes();
        List<ClienteResource> resources = clientes.stream()
                .map(clienteAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(resources,
                linkTo(methodOn(ClienteController.class).listarClientes()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<ClienteResource> crearCliente(@RequestBody Cliente nuevoCliente) {
        Cliente clienteGuardado = clienteService.crearCliente(nuevoCliente);
        return ResponseEntity
                .created(linkTo(methodOn(ClienteController.class).obtenerCliente(clienteGuardado.getIdCliente())).toUri())
                .body(clienteAssembler.toModel(clienteGuardado));
    }

}
