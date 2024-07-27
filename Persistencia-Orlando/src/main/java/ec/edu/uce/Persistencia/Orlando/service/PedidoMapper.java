package ec.edu.uce.Persistencia.Orlando.service;

import ec.edu.uce.Persistencia.Orlando.controller.PedidoDTO;
import ec.edu.uce.Persistencia.Orlando.model.Pedido;
import ec.edu.uce.Persistencia.Orlando.model.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {
    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setClienteId(pedido.getCliente().getId());
        List<Long> productosIds = pedido.getProductos().stream()
                .map(Producto::getId)
                .collect(Collectors.toList());
        dto.setProductosIds(productosIds);
        return dto;
    }
}



