package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Envio")
@Getter
@Setter

public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String direccionEntrega;

    @Column(nullable = false)
    private Date fechaEnvio;

    @Column(nullable = false)
    private Date fechaEntregaEstimada;
}
