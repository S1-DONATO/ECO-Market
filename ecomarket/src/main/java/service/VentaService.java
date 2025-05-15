package service;

import jakarta.transaction.Transactional;
import model.Cliente;
import model.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.VentaRepository;

import java.time.LocalDate;
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

    // solo hacer un update creo
    public String actualizarEstado(){
        return "hola";
    }

    // necesita producto lista para funcionar
    public double calcularTotal(){
        return 1.2+1.3;
    }

    // simplemente listar la mayoria de cosas en la clase creo
    public String generarFactura(Venta venta){
        System.out.println("========================================");
        System.out.println("Fecha de Venta: "+venta.getFechaVenta());
        System.out.println("========================================");
        System.out.println("Factura a: "+venta.getNombre());
        System.out.println("Direccion: "+venta.getDireccionEntrega());
        System.out.println("========================================");
        System.out.println("Productos vendidos:");

        System.out.println("========================================");
        System.out.println("Total venta: $"+venta.getTotal());
        System.out.println("========================================");
        return "Factura generada.";
    }

}
