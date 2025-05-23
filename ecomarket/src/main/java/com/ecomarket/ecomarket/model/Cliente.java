package com.ecomarket.ecomarket.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String metodoPagoPreferido;

    @Column(nullable = false)
    private String direccionEnvio;

    @Column(nullable = false)
    private String numeroTarjeta;

}
