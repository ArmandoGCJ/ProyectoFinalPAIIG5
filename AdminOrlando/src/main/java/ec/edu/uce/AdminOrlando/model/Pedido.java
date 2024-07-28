package ec.edu.uce.AdminOrlando.model;

import java.util.List;

public class Pedido {
    private int id;
    private int clienteId;
    private String descripcion;
    private double precio;
    private List<String> etapas;
    private Cliente cliente; // Referencia al objeto Cliente

    public Pedido() {
    }

    public Pedido(int id, int clienteId, String descripcion, double precio, List<String> etapas, Cliente cliente) {
        this.id = id;
        this.clienteId = clienteId;
        this.descripcion = descripcion;
        this.precio = precio;
        this.etapas = etapas;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<String> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<String> etapas) {
        this.etapas = etapas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        if (etapas == null || etapas.isEmpty()) {
            return "Sin etapas";
        }
        return etapas.get(etapas.size() - 1); // Devuelve la última etapa como el estado actual
    }
}
