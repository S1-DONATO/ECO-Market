package com.ecomarket.ecomarket.service;


import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomarket.ecomarket.repository.ClienteRepository;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(long idCliente){
        return clienteRepository.findById(idCliente).get();
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void delete(Long idCliente){
        clienteRepository.deleteById(idCliente);
    }

    public List<Cliente> busquedaDeUsuario(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

}
