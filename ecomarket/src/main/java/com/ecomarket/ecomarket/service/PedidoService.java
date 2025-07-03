package com.ecomarket.ecomarket.service;

import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.model.Proveedor;
import com.ecomarket.ecomarket.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodosLosPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id){
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
    }

    public Pedido crearPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        return pedidoRepository.findById(id)
                .map(pedidoExistente -> {
                    pedidoExistente.setProveedorPedido(pedidoActualizado.getProveedorPedido());
                    pedidoExistente.setFechaPedido(pedidoActualizado.getFechaPedido());
                    pedidoExistente.setMetodoPago(pedidoActualizado.getMetodoPago());
                    return pedidoRepository.save(pedidoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id + " para actualizar."));
    }

    public void eliminarPedido(Long id){
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pedido no encontrado con ID: " + id + " para eliminar.");
        }
    }

    public List<Pedido> findByIdPedido(Long idPedido){
        return pedidoRepository.findByIdPedido(idPedido);
    }

    public List<Pedido> findByFechaPedido(Date fechaPedido){
        return pedidoRepository.findByFechaPedido(fechaPedido);
    }

    public List<Pedido> findByProveedorPedido(Proveedor proveedor){
        return pedidoRepository.findByProveedorPedido(proveedor);
    }

}
