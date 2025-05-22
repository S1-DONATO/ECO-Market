package com.ecomarket.ecomarket.model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name= "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false)
    private String descripcion;


    @Column(nullable = false)
    private double precio;


    @Column(nullable = false)
    private Integer stock;


    @Column(nullable = false)
    private String categoria;

    @Column(nullable = true)
    private double descuento;

}
