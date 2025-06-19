package com.ecomarket.ecomarket.service;


import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.stereotype.Service;
import com.ecomarket.ecomarket.repository.ClienteRepository;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodosLosClientes(){
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado){
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteActualizado.getNombre());
                    clienteExistente.setApellido(clienteActualizado.getApellido());
                    clienteExistente.setContrasena(clienteActualizado.getContrasena());
                    clienteExistente.setCorreo(clienteActualizado.getCorreo());
                    clienteExistente.setDireccionEnvio(clienteActualizado.getDireccionEnvio());
                    clienteExistente.setMetodoPagoPreferido(clienteActualizado.getMetodoPagoPreferido());
                    clienteExistente.setNumeroTarjeta(clienteActualizado.getNumeroTarjeta());
                    clienteExistente.setTelefono(clienteActualizado.getTelefono());

                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id + " para actualizar."));
    }

    public void eliminarCliente(Long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id + " para eliminar.");
        }
    }

    public Cliente findByNombre(String nombre){
        return clienteRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con nombre: " + nombre));
    }

    public Cliente findByApellido(String apellido){
        return clienteRepository.findByApellido(apellido)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con apellido: " + apellido));
    }

    public Cliente findByCorreo(String correo){
        return clienteRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con correo: " + correo));
    }

    public Cliente findByTelefono(String telefono){
        return clienteRepository.findByTelefono(telefono)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con telefono: " + telefono));
    }

    public Cliente findByDireccionEnvio(String direccionEnvio){
        return clienteRepository.findByDireccionEnvio(direccionEnvio)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con direccion envio: " + direccionEnvio));
    }

    public Cliente findBynumeroTarjeta(String numeroTarjeta){
        return clienteRepository.findBynumeroTarjeta(numeroTarjeta)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con numero tarjeta: " + numeroTarjeta));
    }

}
