package ec.edu.uce.FabricaOrlando.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.FabricaOrlando.model.Client;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/clientes";
    private static final String CONTENT_TYPE = "application/json";

    public Client registrarCliente(Client cliente) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(BASE_URL + "/registrar");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(cliente);
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", CONTENT_TYPE);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getCode();
                if (statusCode == 200) {
                    return objectMapper.readValue(response.getEntity().getContent(), Client.class);
                } else {
                    throw new RuntimeException("Failed to register client. HTTP error code: " + statusCode);
                }
            }
        }
    }

    public Client iniciarSesion(String nombre, String contrasena) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(BASE_URL + "/login");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = String.format("{\"nombre\":\"%s\",\"contrasena\":\"%s\"}", nombre, contrasena);
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", CONTENT_TYPE);

            System.out.println("JSON sent: " + json);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getCode();
                if (statusCode == 200) {
                    return objectMapper.readValue(response.getEntity().getContent(), Client.class);
                } else if (statusCode == 401) {

                    throw new RuntimeException("Usuario o contraseña incorrectos.");
                } else {
                    throw new RuntimeException("Unexpected error. HTTP error code: " + statusCode);
                }
            }
        }
    }
}
