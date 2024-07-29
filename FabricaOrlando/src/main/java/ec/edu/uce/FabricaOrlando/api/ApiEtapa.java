package ec.edu.uce.FabricaOrlando.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.FabricaOrlando.model.Etapa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiEtapa {
    private static final String BASE_URL = "http://localhost:8080/etapas";
    private static final String CONTENT_TYPE = "application/json";

    public static List<Etapa> getAll() {
        List<Etapa> etapaList = null;
        try {
            URL url = new URL(BASE_URL + "/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", CONTENT_TYPE);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();
            etapaList = mapper.readValue(sb.toString(), new TypeReference<>() {});

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return etapaList;
    }

    public static List<Etapa> getEtapasPorProductoId(Long productoId) {
        List<Etapa> etapaList = null;
        try {
            URL url = new URL(BASE_URL + "/producto/" + productoId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", CONTENT_TYPE);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            ObjectMapper mapper = new ObjectMapper();
            etapaList = mapper.readValue(sb.toString(), new TypeReference<>() {});

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return etapaList;
    }
}
