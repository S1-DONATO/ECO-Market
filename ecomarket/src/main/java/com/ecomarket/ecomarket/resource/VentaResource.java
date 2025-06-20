package com.ecomarket.ecomarket.resource;

import com.ecomarket.ecomarket.model.Cliente;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.model.Venta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;

@Relation(collectionRelation = "ventas", itemRelation = "venta")
@Data
@EqualsAndHashCode(callSuper = true)
public class VentaResource extends RepresentationModel<VentaResource> {

    private Long idVenta;
    private Cliente nombre;
    private List<Producto> productos;
    private String estadoVenta;
    private String metodoPago;
    private String direccionEntrega;
    private Date fechaVenta;

    public VentaResource() {
    }

    public VentaResource(Venta venta) {
        this.idVenta = venta.getIdVenta();
        this.nombre = venta.getNombre();
        this.productos = venta.getProductos();
        this.estadoVenta = venta.getEstadoVenta();
        this.metodoPago = venta.getMetodoPago();
        this.direccionEntrega = venta.getDireccionEntrega();
        this.fechaVenta = venta.getFechaVenta();
    }

}
