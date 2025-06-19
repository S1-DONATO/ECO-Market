package com.ecomarket.ecomarket.assemblers;

import com.ecomarket.ecomarket.controller.ClienteControllerV2; // Asegúrate de que sea ProductoControllerV2 si ese es el que usas
import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.resource.ClienteResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteResourceAssembler extends RepresentationModelAssemblerSupport<Cliente, ClienteResource> {

    public ClienteResourceAssembler(){
        super(ClienteControllerV2.class, ClienteResource.class);
    }

    @Override
    public ClienteResource toModel(Cliente entity){
        ClienteResource resource = instantiateModel(entity);

        resource.setIdCliente(entity.getIdCliente());
        resource.setNombre(entity.getNombre());
        resource.setApellido(entity.getApellido());
        resource.setCorreo(entity.getCorreo());
        resource.setTelefono(entity.getTelefono());
        resource.setContrasena(entity.getContrasena());
        resource.setMetodoPagoPreferido(entity.getMetodoPagoPreferido());
        resource.setDireccionEnvio(entity.getDireccionEnvio());
        resource.setNumeroTarjeta(entity.getNumeroTarjeta());

        resource.add(linkTo(methodOn(ClienteControllerV2.class).buscar(entity.getIdCliente())).withSelfRel());
        resource.add(linkTo(methodOn(ClienteControllerV2.class).eliminar(entity.getIdCliente())).withRel("eliminar"));
        resource.add(linkTo(methodOn(ClienteControllerV2.class).actualizar(entity.getIdCliente(), entity)).withRel("actualizar"));
        resource.add(linkTo(methodOn(ClienteControllerV2.class).listar()).withRel("clientes"));

        return resource;
    }

}
