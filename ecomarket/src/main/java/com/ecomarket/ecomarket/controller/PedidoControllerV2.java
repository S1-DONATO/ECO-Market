package com.ecomarket.ecomarket.controller;

import com.ecomarket.ecomarket.assemblers.PedidoResourceAssembler;
import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.repository.PedidoRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoControllerV2 {

    private final PedidoRepository repository;
    private final PedidoResourceAssembler assembler;

    public PedidoControllerV2(PedidoRepository repository, PedidoResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Pedido>> getAllPedidos() {
        List<EntityModel<Pedido>> pedidos = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos,
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pedido> getPedido(@PathVariable Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        return assembler.toModel(pedido);
    }
}
