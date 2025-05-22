package com.ecomarket.ecomarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "administradorSistema")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdministradorSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdministradorSistema;

    @Column(nullable = false)
    private String nombreAdministradorSistema;

    @Column(nullable = false)
    private String correoAdministradorSistema;

    @Column(nullable = false)
    private String telefonoAdministradorSistema;

    @Column(nullable = false)
    private List<String> permisosAsignados;

}