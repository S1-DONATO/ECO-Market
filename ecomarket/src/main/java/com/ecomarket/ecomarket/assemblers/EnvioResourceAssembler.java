package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.EnvioControllerV2;
import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.resource.EnvioResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnvioResourceAssembler extends RepresentationModelAssemblerSupport<Envio, EnvioResource> {

    public EnvioResourceAssembler() {
        super(EnvioControllerV2.class, EnvioResource.class);
    }

    @Override
    public EnvioResource toModel(Envio entity) {
        EnvioResource resource = instantiateModel(entity);

        resource.setIdEnvio(entity.getIdEnvio());
        resource.setDestino(entity.getDestino());
        resource.setEstado(entity.getEstado());
        resource.setFechaEnvio(entity.getFechaEnvio());
        resource.setOrigen(entity.getOrigen());
        resource.setFechaEntregaEstimada(entity.getFechaEntregaEstimada());
        resource.setVenta(entity.getVenta());
        resource.setDireccionEntrega(entity.getDireccionEntrega());

        resource.add(linkTo(methodOn(EnvioControllerV2.class).buscar(entity.getIdEnvio())).withSelfRel());
        resource.add(linkTo(methodOn(EnvioControllerV2.class).eliminar(entity.getIdEnvio())).withRel("eliminar"));
        resource.add(linkTo(methodOn(EnvioControllerV2.class).actualizar(entity.getIdEnvio(), entity)).withRel("actualizar"));
        resource.add(linkTo(methodOn(EnvioControllerV2.class).listar()).withRel("envios"));

        return resource;
    }

}
