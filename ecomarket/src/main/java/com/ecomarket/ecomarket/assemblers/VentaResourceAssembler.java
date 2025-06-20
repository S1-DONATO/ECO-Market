package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.VentaControllerV2;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import com.ecomarket.ecomarket.model.Venta;
import com.ecomarket.ecomarket.resource.VentaResource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VentaResourceAssembler extends RepresentationModelAssemblerSupport<Venta, VentaResource> {

    public VentaResourceAssembler() {
        super(VentaControllerV2.class, VentaResource.class);
    }

    @Override
    public VentaResource toModel(Venta entity) {
        VentaResource resource = instantiateModel(entity);

        resource.setIdVenta(entity.getIdVenta());
        resource.setNombre(entity.getNombre());
        resource.setProductos(entity.getProductos());
        resource.setEstadoVenta(entity.getEstadoVenta());
        resource.setMetodoPago(entity.getMetodoPago());
        resource.setDireccionEntrega(entity.getDireccionEntrega());
        resource.setFechaVenta(entity.getFechaVenta());

        resource.add(linkTo(methodOn(VentaControllerV2.class).buscar(entity.getIdVenta())).withSelfRel());
        resource.add(linkTo(methodOn(VentaControllerV2.class).eliminar(entity.getIdVenta())).withRel("eliminar"));
        resource.add(linkTo(methodOn(VentaControllerV2.class).actualizar(entity.getIdVenta(), entity)).withRel("actualizar"));
        resource.add(linkTo(methodOn(VentaControllerV2.class).listar()).withRel("productos"));

        return resource;
    }

}
