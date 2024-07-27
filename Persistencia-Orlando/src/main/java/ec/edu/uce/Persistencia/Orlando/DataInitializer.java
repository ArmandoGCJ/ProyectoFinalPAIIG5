package ec.edu.uce.Persistencia.Orlando;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import ec.edu.uce.Persistencia.Orlando.model.Etapa;
import ec.edu.uce.Persistencia.Orlando.model.Producto;
import ec.edu.uce.Persistencia.Orlando.service.ClienteService;
import ec.edu.uce.Persistencia.Orlando.service.PedidoService;
import ec.edu.uce.Persistencia.Orlando.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    @Autowired
    public DataInitializer(ClienteService clienteService, ProductoService productoService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
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

        producto.getEtapas().add(etapa1);
        producto.getEtapas().add(etapa2);

        productoService.guardarProducto(producto);

        // Crear otro producto para añadir al pedido
        Producto producto2 = new Producto();
        producto2.setNombre("Pan");
        producto2.setPrecio(5.00);
        producto2.setDescripcion("Pan integral");

        Etapa etapa3 = new Etapa();
        etapa3.setDescripcion("Amasar");
        etapa3.setDuracion(7);
        etapa3.setProducto(producto2);

        Etapa etapa4 = new Etapa();
        etapa4.setDescripcion("Hornear");
        etapa4.setDuracion(15);
        etapa4.setProducto(producto2);

        producto2.getEtapas().add(etapa3);
        producto2.getEtapas().add(etapa4);

        productoService.guardarProducto(producto2);

        // Agregar un pedido
        List<Long> productosIds = Arrays.asList(producto.getId(), producto2.getId());
        pedidoService.guardarPedido(cliente.getId(), productosIds);
    }
}