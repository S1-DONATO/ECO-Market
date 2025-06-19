package com.ecomarket.ecomarket.repository;


import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNombre(String nombre);
    Optional<Cliente> findByApellido(String apellido);
    Optional<Cliente> findByCorreo(String correo);
    Optional<Cliente> findByTelefono(String telefono);
    Optional<Cliente> findByDireccionEnvio(String direccionEnvio);
    Optional<Cliente> findBynumeroTarjeta(String numeroTarjeta);

}
