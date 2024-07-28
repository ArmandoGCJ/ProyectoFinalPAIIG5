package ec.edu.uce.FabricaOrlando.Controller;

import ec.edu.uce.FabricaOrlando.api.ApiClient;
import ec.edu.uce.FabricaOrlando.api.ApiProduct;
import ec.edu.uce.FabricaOrlando.model.Client;

import java.io.IOException;

public class Container {
    private ApiClient apiClient;
    private ApiProduct apiProduct;

    public Container() {
        apiClient = new ApiClient();
    }

    public void registrarCliente(Client client) throws IOException {
        apiClient.registrarCliente(client);
    }

    public void iniciarSesion(String nombre, String password) throws IOException {
        apiClient.iniciarSesion(nombre, password);
    }

    public void getAllProducts(){
        apiProduct.getAll();
    }



}
