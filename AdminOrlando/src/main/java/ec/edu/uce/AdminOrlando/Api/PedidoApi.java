package ec.edu.uce.AdminOrlando.Api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.AdminOrlando.model.Pedido;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;
import java.util.List;

public class PedidoApi {
    private static final String BASE_URL = "http://localhost:8080/pedidos";

    public List<Pedido> obtenerTodosLosPedidos() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getCode() == 200) {
                return objectMapper.readValue(response.getEntity().getContent(), objectMapper.getTypeFactory().constructCollectionType(List.class, Pedido.class));
            } else {
                throw new RuntimeException("Failed to fetch orders");
            }
        }
    }

    public void agregarPedido(Pedido pedido) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(pedido);
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getCode() != 201) {
                throw new RuntimeException("Failed to add order");
            }
        }
    }

    public void eliminarPedido(Long id) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(BASE_URL + "/" + id);
        try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
            if (response.getCode() != 204) {
                throw new RuntimeException("Failed to delete order");
            }
        }
    }

    public void procesarPedido(Long orderId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(BASE_URL + "/" + orderId + "/procesar");
        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
            if (response.getCode() != 200) {
                throw new RuntimeException("Failed to process order");
            }
        }
    }

}
