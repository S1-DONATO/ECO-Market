package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.PedidoControllerV2;

import com.ecomarket.ecomarket.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResourceAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControllerV2.class).getPedido(pedido.getIdPedido())).withSelfRel(),
                linkTo(methodOn(PedidoControllerV2.class).getAllPedidos()).withRel("pedidos")
        );
    }
}
