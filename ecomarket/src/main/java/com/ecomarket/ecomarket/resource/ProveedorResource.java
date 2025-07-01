package com.ecomarket.ecomarket.resource;

import com.ecomarket.ecomarket.model.Producto;
import org.springframework.hateoas.RepresentationModel;


import com.ecomarket.ecomarket.model.Proveedor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Relation(collectionRelation = "proveedores", itemRelation = "proveedor")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProveedorResource extends RepresentationModel<ProveedorResource> {

    private Long idProveedor;
    private String nombreProveedor;
    private String telefonoProveedor;
    private String correo;
    private Producto productoSuministrado;

    public ProveedorResource() {
    }

    public ProveedorResource(Proveedor proveedor) {

        this.idProveedor = proveedor.getIdProveedor();
        this.nombreProveedor = proveedor.getNombreProveedor();
        this.telefonoProveedor = proveedor.getTelefonoProveedor();
        this.correo = proveedor.getCorreo();
        this.productoSuministrado = proveedor.getProductoSuministrado();

    }

}
