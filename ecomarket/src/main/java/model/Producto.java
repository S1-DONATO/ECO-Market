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

<<<<<<< HEAD

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


=======
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

>>>>>>> 451d513c84a1d26c0a9faed1221f1091b55223e3
    @Column(nullable = true)
    private double descuento;

}
