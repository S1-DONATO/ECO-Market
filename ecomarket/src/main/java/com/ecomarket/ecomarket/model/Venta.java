package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "venta")
    private Cliente nombre;

    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

    @Column(nullable = false)
    private String estadoVenta;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private String direccionEntrega;

    @Column(nullable = false)
    private Date fechaVenta;


}
