package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.PedidoControllerV2;
import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.resource.PedidoResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResourceAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResource>{

    public PedidoResourceAssembler() {
        super(PedidoControllerV2.class, PedidoResource.class);
    }

    @Override
    public PedidoResource toModel(Pedido entity) {
        PedidoResource resource = instantiateModel(entity);

        resource.setIdPedido(entity.getIdPedido());
        resource.setProveedorPedido(entity.getProveedorPedido());
        resource.setFechaPedido(entity.getFechaPedido());
        resource.setMetodoPago(entity.getMetodoPago());

        resource.add(linkTo(methodOn(PedidoControllerV2.class).buscar(entity.getIdPedido())).withSelfRel());
        resource.add(linkTo(methodOn(PedidoControllerV2.class).eliminar(entity.getIdPedido())).withRel("eliminar"));
        resource.add(linkTo(methodOn(PedidoControllerV2.class).actualizar(entity.getIdPedido(), entity)).withRel("actualizar"));
        resource.add(linkTo(methodOn(PedidoControllerV2.class).listar()).withRel("pedidos"));

        return resource;
    }
}
