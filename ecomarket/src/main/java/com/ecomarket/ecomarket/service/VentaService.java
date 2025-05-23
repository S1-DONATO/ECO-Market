package com.ecomarket.ecomarket.service;

import jakarta.transaction.Transactional;
import com.ecomarket.ecomarket.model.Producto;
import com.ecomarket.ecomarket.model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecomarket.ecomarket.repository.VentaRepository;

import java.util.List;

@Service
@Transactional

public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findall(){
        return ventaRepository.findAll();
    }

    public Venta findById(long id){
        return ventaRepository.findById(id).get();
    }

    public Venta save(Venta venta){
        return ventaRepository.save(venta);
    }

    public void delete(Long id){
        ventaRepository.deleteById(id);
    }

    // solo hacer un update creo, no se si es necesario
    public String actualizarEstado(){



        return "Estado actualizado.";
    }


    public double calcularTotal(Long id){
        Venta venta = ventaRepository.findById(id).orElse(new Venta());
        double total = 0;
        for(Producto i: venta.getProductos()){
            total += i.getPrecio();
        }
        return total;
    }

    public String generarFactura(Long id){
        Venta venta = ventaRepository.findById(id).orElse(new Venta());
        double total=0;
        System.out.println("========================================");
        System.out.println("N° de venta: "+venta.getIdVenta());
        System.out.println("Fecha de venta: "+venta.getFechaVenta());
        System.out.println("========================================");
        System.out.println("Factura a: "+venta.getNombre());
        System.out.println("Direccion: "+venta.getDireccionEntrega());
        System.out.println("========================================");
        System.out.println("Productos vendidos:");
        int i=0;
        while (i < venta.getProductos().size()) {
            System.out.println((1+i)+". "+venta.getProductos().get(i));
            i=i+1;
        }
        System.out.println("========================================");
        System.out.println("Total venta: $"+total);
        System.out.println("========================================");
        return "Factura generada.";
    }

}
