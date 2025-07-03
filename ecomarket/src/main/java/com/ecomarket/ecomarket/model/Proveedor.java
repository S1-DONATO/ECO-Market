package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name= "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(nullable = false)
    private String nombreProveedor;

    @Column(nullable = false)
    private String telefonoProveedor;

    @Column(nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private Producto productoSuministrado;

}
