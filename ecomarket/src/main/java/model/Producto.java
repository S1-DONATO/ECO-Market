package model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;


    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false)
    private String descripcion;


    @Column(nullable = false)
    private double precio;


    @Column(nullable = false)
    private int stock;


    @Column(nullable = false)
    private String categoria;


    @Column(nullable = true)
    private double descuento;

}
