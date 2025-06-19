package com.ecomarket.ecomarket.resource;

import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation; // <--- ¡Esta es la importación CLAVE!
import lombok.Data;
import lombok.EqualsAndHashCode;

@Relation(collectionRelation = "clientes", itemRelation = "cliente")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteResource extends RepresentationModel<ClienteResource> {

    private Long idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;
    private String metodoPagoPreferido;
    private String direccionEnvio;
    private String numeroTarjeta;

    public ClienteResource() {
    }

    public ClienteResource(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.correo = cliente.getCorreo();
        this.telefono = cliente.getTelefono();
        this.contrasena = cliente.getContrasena();
        this.metodoPagoPreferido = cliente.getMetodoPagoPreferido();
        this.direccionEnvio = cliente.getDireccionEnvio();
        this.numeroTarjeta = cliente.getNumeroTarjeta();
    }

}
