package com.ecomarket.ecomarket.resource;

import com.ecomarket.ecomarket.model.Envio;
import com.ecomarket.ecomarket.model.Venta;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Relation(collectionRelation = "envios", itemRelation = "envio")
@Data
@EqualsAndHashCode(callSuper = true)
public class EnvioResource extends RepresentationModel<EnvioResource> {

    private Long idEnvio;
    private Venta venta;
    private String origen;
    private String destino;
    private String estado;
    private String direccionEntrega;
    private Date fechaEnvio;
    private Date fechaEntregaEstimada;

    public EnvioResource() {
    }

    public EnvioResource(Envio envio) {
        this.idEnvio = envio.getIdEnvio();
        this.venta = envio.getVenta();
        this.origen = envio.getOrigen();
        this.destino = envio.getDestino();
        this.estado = envio.getEstado();
        this.direccionEntrega = envio.getDireccionEntrega();
        this.fechaEnvio = envio.getFechaEnvio();
        this.fechaEntregaEstimada = new Date();
    }

}