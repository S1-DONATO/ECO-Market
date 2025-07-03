package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.PedidoResourceAssembler;
import com.ecomarket.ecomarket.resource.PedidoResource;
import com.ecomarket.ecomarket.service.PedidoService;
import com.ecomarket.ecomarket.model.Pedido;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoControllerV2 {

    private final PedidoService pedidoService;
    private final PedidoResourceAssembler pedidoAssembler;

    public PedidoControllerV2(PedidoService pedidoService, PedidoResourceAssembler pedidoAssembler) {
        this.pedidoService = pedidoService;
        this.pedidoAssembler = pedidoAssembler;
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<PedidoResource>> listar(){
        List<Pedido> pedidos = pedidoService.listarTodosLosPedidos();

        if (pedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<PedidoResource> pedidosResources = pedidos.stream()
                .map(pedidoAssembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<PedidoResource> resources = CollectionModel.of(pedidosResources,
                linkTo(methodOn(PedidoControllerV2.class).listar()).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PedidoResource> guardar(@RequestBody Pedido pedido){
        Pedido pedidoNuevo = pedidoService.crearPedido(pedido);
        PedidoResource pedidoResource = pedidoAssembler.toModel(pedidoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResource);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PedidoResource> buscar(@PathVariable Long id){
        try{
            Pedido pedido = pedidoService.obtenerPedidoPorId(id);
            PedidoResource pedidoResource = pedidoAssembler.toModel(pedido);
            return ResponseEntity.ok(pedidoResource);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PedidoResource> actualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        try{
            Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
            PedidoResource pedResource = pedidoAssembler.toModel(pedidoActualizado);
            return ResponseEntity.ok(pedResource);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try{
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
