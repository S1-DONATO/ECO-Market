package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private Integer idProveedor;

    @Column(nullable = false)
    private String nombreProveedor;

    @Column(nullable = false)
    private String telefonoProveedor;

    @Column(nullable = false)
    private String correo;

    //@Column(nullable = false)
    //private List<Producto> productosSuminstrado;


}
