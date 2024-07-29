package ec.edu.uce.AdminOrlando.Api;

import ec.edu.uce.AdminOrlando.model.Cliente;
import ec.edu.uce.AdminOrlando.model.LoginRequest;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ClienteApi {
    private static final String BASE_URL = "http://localhost:8080/clientes";

    public Cliente registrarCliente(Cliente cliente) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL + "/registrar");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cliente);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getCode() == 200) {
                return objectMapper.readValue(response.getEntity().getContent(), Cliente.class);
            } else {
                throw new RuntimeException("Failed to register client");
            }
        }
    }

    public Cliente iniciarSesion(String nombre, String contrasena) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL + "/login");
        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNombre(nombre);
        loginRequest.setContrasena(contrasena);
        String json = objectMapper.writeValueAsString(loginRequest);
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getCode() == 200) {
                return objectMapper.readValue(response.getEntity().getContent(), Cliente.class);
            } else {
                throw new RuntimeException("Failed to log in");
            }
        }
    }
}
