package ec.edu.uce.Persistencia.Orlando.service;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import ec.edu.uce.Persistencia.Orlando.model.Pedido;
import ec.edu.uce.Persistencia.Orlando.model.Producto;
import ec.edu.uce.Persistencia.Orlando.repository.ClienteRepository;
import ec.edu.uce.Persistencia.Orlando.repository.PedidoRepository;
import ec.edu.uce.Persistencia.Orlando.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private  PedidoRepository pedidoRepository;
    @Autowired
    private  ClienteRepository clienteRepository;
    @Autowired
    private  ProductoRepository productoRepository;

    public Pedido guardarPedido(Long clienteId, List<Long> productosIds) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        List<Producto> productos = productoRepository.findAllById(productosIds);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProductos(productos);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepository.findAll();
    }
}
