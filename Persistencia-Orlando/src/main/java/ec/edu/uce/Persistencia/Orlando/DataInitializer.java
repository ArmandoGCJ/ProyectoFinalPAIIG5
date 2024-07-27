package ec.edu.uce.Persistencia.Orlando;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import ec.edu.uce.Persistencia.Orlando.model.Etapa;
import ec.edu.uce.Persistencia.Orlando.model.Producto;
import ec.edu.uce.Persistencia.Orlando.service.ClienteService;
import ec.edu.uce.Persistencia.Orlando.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteService clienteService;
    private final ProductoService productoService;

    @Autowired
    public DataInitializer(ClienteService clienteService, ProductoService productoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Agregar un cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setContrasena("password123");
        cliente.setRol("ADMIN");
        clienteService.guardarCliente(cliente);

        // Agregar un producto con etapas
        Producto producto = new Producto();
        producto.setNombre("Galleta");
        producto.setPrecio(10.50);
        producto.setDescripcion("Galleta de chocolate");

        Etapa etapa1 = new Etapa();
        etapa1.setDescripcion("Mezclar ingredientes");
        etapa1.setDuracion(5);
        etapa1.setProducto(producto);

        Etapa etapa2 = new Etapa();
        etapa2.setDescripcion("Hornear");
        etapa2.setDuracion(10);
        etapa2.setProducto(producto);

        producto.agregarEtapa(etapa1);
        producto.agregarEtapa(etapa2);

        productoService.guardarProducto(producto);
    }
}
