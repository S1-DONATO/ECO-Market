package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pedido")
@Getter
@Setter

public class Pedido {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long idPedido;

 @ManyToOne
 @JoinColumn(name = "pedido")
 private Proveedor proveedorPedido;

 @Column(nullable = false)
 private Date fechaPedido;

 @Column(nullable = false)
 private String metodoPago;

 @ManyToMany
 @JoinTable(
         name = "pedido_producto",
         joinColumns = @JoinColumn(name = "pedido_id"),
         inverseJoinColumns = @JoinColumn(name = "producto_id")
 ) private List<Producto> productos;

}
