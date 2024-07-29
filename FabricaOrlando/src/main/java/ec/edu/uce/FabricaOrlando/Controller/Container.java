package ec.edu.uce.FabricaOrlando.Controller;

import ec.edu.uce.FabricaOrlando.api.ApiClient;
import ec.edu.uce.FabricaOrlando.api.ApiEtapa;
import ec.edu.uce.FabricaOrlando.api.ApiProduct;
import ec.edu.uce.FabricaOrlando.model.Client;
import ec.edu.uce.FabricaOrlando.model.Etapa;
import ec.edu.uce.FabricaOrlando.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Container {
    private final ApiClient apiClient;
    private final ApiProduct apiProduct;
    private final ApiEtapa apiEtapa;
    ExecutorService executors;

    public Container() {
        apiClient = new ApiClient();
        apiProduct = new ApiProduct();
        apiEtapa = new ApiEtapa();
        executors = Executors.newSingleThreadExecutor();

    }

    public void registrarCliente(Client client) throws IOException {
        apiClient.registrarCliente(client);
    }

    public void iniciarSesion(String nombre, String password) throws IOException {
        apiClient.iniciarSesion(nombre, password);
    }

    public List<Product> getAllProducts(){
        return apiProduct.getAll();
    }

    public List<Etapa> getAllEtapa(){
        return apiEtapa.getAll();
    }

    public List<Etapa> getEtapasPorProductoId(Long productoId) {
        return apiEtapa.getEtapasPorProductoId(productoId);
    }

}
