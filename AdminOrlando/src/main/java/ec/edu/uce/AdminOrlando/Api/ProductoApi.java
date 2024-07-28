package ec.edu.uce.AdminOrlando.Api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.AdminOrlando.model.Producto;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;
import java.util.List;

public class ProductoApi {
    private static final String BASE_URL = "http://localhost:8080/productos";
    private ObjectMapper objectMapper;

    public ProductoApi() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Producto> obtenerTodosLosProductos() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getCode() == 200) {
                return objectMapper.readValue(response.getEntity().getContent(), objectMapper.getTypeFactory().constructCollectionType(List.class, Producto.class));
            } else {
                throw new RuntimeException("Failed to fetch products");
            }
        }
    }

    public void agregarProducto(Producto producto) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASE_URL);
        String json = objectMapper.writeValueAsString(producto);
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            if (response.getCode() != 201) {
                throw new RuntimeException("Failed to add product");
            }
        }
    }

    public void eliminarProducto(Long id) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(BASE_URL + "/" + id);
        try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
            if (response.getCode() != 204) {
                throw new RuntimeException("Failed to delete product");
            }
        }
    }

    public Producto obtenerProductoPorNombre(String nombre) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(BASE_URL + "/nombre/" + nombre);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if (response.getCode() == 200) {
                return objectMapper.readValue(response.getEntity().getContent(), Producto.class);
            } else {
                throw new RuntimeException("Failed to fetch product by name");
            }
        }
    }
}
