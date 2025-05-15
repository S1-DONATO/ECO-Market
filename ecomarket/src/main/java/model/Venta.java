package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Cliente;

import java.util.List;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @Column(nullable = false)
    private Cliente idCliente;

    @Column(nullable = false)
    private List<Producto> productosVenta;

    @Column(nullable = false)
    private String estadoVenta;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private String direccionEntrega;

    @Column(nullable = false)
    private double total;

}
