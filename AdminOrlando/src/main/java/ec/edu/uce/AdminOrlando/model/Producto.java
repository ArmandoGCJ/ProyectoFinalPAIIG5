package ec.edu.uce.AdminOrlando.model;

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public double getPrecio() {
        return precio;
    }

}
