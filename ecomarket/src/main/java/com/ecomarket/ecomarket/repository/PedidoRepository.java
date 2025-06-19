package com.ecomarket.ecomarket.repository;

import com.ecomarket.ecomarket.model.Pedido;
import com.ecomarket.ecomarket.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByIdPedido(Long idPedido);
    List<Pedido> findByFechaPedido(Date fechaPedido);
    List<Pedido> findByProveedorPedido(Proveedor proveedor);

}
