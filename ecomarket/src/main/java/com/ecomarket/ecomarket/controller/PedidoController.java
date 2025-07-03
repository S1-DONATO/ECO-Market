package com.ecomarket.ecomarket.controller;


import com.ecomarket.ecomarket.assemblers.ProductoResourceAssembler;
import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.resource.PedidoResource;
import com.ecomarket.ecomarket.assemblers.PedidoResourceAssembler;
import com.ecomarket.ecomarket.service.PedidoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ECO-Market/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoResourceAssembler pedidoAssembler;

    public PedidoController(PedidoService pedidoService, PedidoResourceAssembler pedidoAssembler) {
        this.pedidoService = pedidoService;
        this.pedidoAssembler = pedidoAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResource> obtenerPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        if (pedido == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedidoAssembler.toModel(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResource> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoActualizado) {
        Pedido updatedPedido = pedidoService.actualizarPedido(id, pedidoActualizado);
        if (updatedPedido == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedidoAssembler.toModel(updatedPedido));
    }

    @GetMapping
    public CollectionModel<PedidoResource> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodosLosPedidos();
        List<PedidoResource> resources = pedidos.stream()
                .map(pedidoAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(resources,
                linkTo(methodOn(ProductoController.class).listarProductos()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<PedidoResource> crearPedido(@RequestBody Pedido nuevoPedido) {
        Pedido pedidoGuardado = pedidoService.crearPedido(nuevoPedido);
        return ResponseEntity
                .created(linkTo(methodOn(PedidoController.class).obtenerPedido(pedidoGuardado.getIdPedido())).toUri())
                .body(pedidoAssembler.toModel(pedidoGuardado));
    }

}
