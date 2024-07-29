package ec.edu.uce.Persistencia.Orlando.controller;

import ec.edu.uce.Persistencia.Orlando.model.Pedido;
import ec.edu.uce.Persistencia.Orlando.service.PedidoMapper;
import ec.edu.uce.Persistencia.Orlando.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/create")
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody CrearPedidoRequest request) {
        Pedido nuevoPedido = pedidoService.guardarPedido(request.getClienteId(), request.getProductosIds());
        PedidoDTO pedidoDTO = PedidoMapper.toDTO(nuevoPedido);
        return ResponseEntity.ok(pedidoDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();
        List<PedidoDTO> pedidoDTOs = pedidos.stream()
                .map(PedidoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidoDTOs);
    }
}