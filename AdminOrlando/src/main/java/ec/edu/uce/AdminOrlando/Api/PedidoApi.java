package ec.edu.uce.AdminOrlando.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.AdminOrlando.model.Pedido;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.List;

public class PedidoApi {
    private static final String BASE_URL = "http://localhost:8080/pedidos";
    private final ObjectMapper objectMapper;

    public PedidoApi() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Pedido> obtenerTodosLosPedidos() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(BASE_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getCode();
                if (statusCode == 200) {
                    return objectMapper.readValue(response.getEntity().getContent(),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class));
                } else {
                    String errorMessage = "Error al obtener pedidos: Código de estado " + statusCode;
                    if (statusCode == 404) {
                        errorMessage += " - Endpoint no encontrado";
                    }
                    System.err.println(errorMessage);
                    throw new RuntimeException(errorMessage);
                }
            }
        } catch (IOException e) {
            System.err.println("Error de IO al obtener pedidos: " + e.getMessage());
            throw e;
        }
    }
}
