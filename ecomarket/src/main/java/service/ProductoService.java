package service;

import jakarta.transaction.Transactional;
import model.Producto;
import repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class ProductoService {

    @Autowired

    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }
    //public List<Producto> findById(long id){
   //     return productoRepository.findById().get();
    //}
}
