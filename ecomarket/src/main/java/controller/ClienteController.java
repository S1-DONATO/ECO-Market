package controller;


import model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/ECO-Market/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>>listarSinParametros(){
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("{nombre}")
    public ResponseEntity<List<Cliente>>buscarPorNombre(String nombre){
        List<Cliente> clienteNombre = clienteService.busquedaDeUsuario(nombre);
        if (clienteNombre.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteNombre);
    }

}
