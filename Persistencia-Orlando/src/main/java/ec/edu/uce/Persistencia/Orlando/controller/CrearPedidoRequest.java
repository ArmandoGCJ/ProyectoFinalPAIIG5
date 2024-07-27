package ec.edu.uce.Persistencia.Orlando.controller;

import java.util.List;

public class CrearPedidoRequest {
    private Long clienteId;
    private List<Long> productosIds;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getProductosIds() {
        return productosIds;
    }

    public void setProductosIds(List<Long> productosIds) {
        this.productosIds = productosIds;
    }
}