package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor

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

    @Column(nullable = false)
    private List<Producto> productosSuminstrado;

}
