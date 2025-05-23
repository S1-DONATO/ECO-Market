package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByIdPedido(Long idPedido);
    List<Pedido> findByFechaPedido(Date fechaPedido);
    List<Pedido> findByproveedor(Long idProveedor);

}
