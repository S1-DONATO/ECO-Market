package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.ProveedorControllerV2;
import com.ecomarket.ecomarket.model.Proveedor;
import com.ecomarket.ecomarket.resource.ProveedorResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProveedorResourceAssembler extends RepresentationModelAssemblerSupport<Proveedor, ProveedorResource> {

    public ProveedorResourceAssembler() {
        super(ProveedorControllerV2.class, ProveedorResource.class);
    }

    @Override
    public ProveedorResource toModel(Proveedor entity) {
        ProveedorResource resource = instantiateModel(entity);

        resource.setIdProveedor(entity.getIdProveedor());
        resource.setNombreProveedor(entity.getNombreProveedor());
        resource.setCorreo(entity.getCorreo());
        resource.setTelefonoProveedor(entity.getTelefonoProveedor());
        resource.setProductoSuministrado(entity.getProductoSuministrado());

        resource.add(linkTo(methodOn(ProveedorControllerV2.class).buscar(entity.getIdProveedor())).withSelfRel());
        resource.add(linkTo(methodOn(ProveedorControllerV2.class).eliminar(entity.getIdProveedor())).withRel("eliminar"));
        resource.add(linkTo(methodOn(ProveedorControllerV2.class).actualizar(entity.getIdProveedor(), entity)).withRel("actualizar"));
        resource.add(linkTo(methodOn(ProveedorControllerV2.class).listar()).withRel("proveedores"));

        return resource;
    }

}
