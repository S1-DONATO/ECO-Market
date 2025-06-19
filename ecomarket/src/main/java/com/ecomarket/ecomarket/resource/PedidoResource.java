package com.ecomarket.ecomarket.resource;

import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.model.Proveedor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation; // <--- ¡Esta es la importación CLAVE!
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Relation(collectionRelation = "pedidos", itemRelation = "pedido")
@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoResource extends RepresentationModel<ProductoResource>{

    private Long idPedido;
    private Proveedor proveedorPedido;
    private Date fechaPedido;
    private String metodoPago;

    public PedidoResource(){

    }

    public PedidoResource(Pedido pedido) {

        this.idPedido = pedido.getIdPedido();
        this.proveedorPedido = pedido.getProveedorPedido();
        this.fechaPedido = pedido.getFechaPedido();
        this.metodoPago = pedido.getMetodoPago();

    }
}
