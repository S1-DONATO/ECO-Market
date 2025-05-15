package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Pedido")
public class Pedido {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int idPedido;

 @Column(nullable = false)
    private int idProveedor;

 @Column(nullable = false)
    private Date fechaPedido;

 @Column(nullable = false)
    private String metodoPago;


 @Column(nullable = false)
 private Map<Producto,Integer> Producto;

 @Column(nullable = false)
    private double total;


}
