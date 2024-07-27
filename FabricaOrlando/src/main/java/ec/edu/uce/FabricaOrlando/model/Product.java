package ec.edu.uce.FabricaOrlando.model;

import java.util.List;


public class Product {

    private Long id;

    private String name;

    private String imagenPath;

    private String details;


    public Product(String imagenPath, String details) {
        this.imagenPath = imagenPath;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagenPath() {
        return imagenPath;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
