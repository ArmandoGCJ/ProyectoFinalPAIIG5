package ec.edu.uce.Persistencia.Orlando.service;

import ec.edu.uce.Persistencia.Orlando.model.Producto;
import ec.edu.uce.Persistencia.Orlando.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
}
