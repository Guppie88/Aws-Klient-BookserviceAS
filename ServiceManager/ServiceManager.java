package AWSklientAS.ServiceManager;

import AWSklientAS.Models.Author;
import AWSklientAS.Models.Books;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceManager {

    static CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper för JSON

    // Skicka en GET-förfrågan
    public static void sendGetRequest(String uri) throws IOException, ParseException {
        HttpGet request = new HttpGet(uri);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response.getCode() != 200) {
                System.out.println("Fel! Statuskod: " + response.getCode() + " vid GET-förfrågan till: " + uri);
                return;
            }

            // Packa upp svaret
            HttpEntity entity = response.getEntity();
            String jsonResp = EntityUtils.toString(entity);

            // Tolka JSON-svaret och skriv ut information om böcker
            ArrayList<Books> books = mapper.readValue(jsonResp, new TypeReference<>() {});
            books.forEach(book -> System.out.println(book.getTitle() + " skriven av " + book.getAuthor().getName()));
        }
    }

    // Skicka en POST-förfrågan med en bok
    public static void sendPostBookRequest(String uri, Books book) throws IOException, ParseException {
        if (book == null) {
            System.out.println("Boken är null och kan inte skickas.");
            return;
        }

        HttpPost postRequest = new HttpPost(uri);

        try {
            // Konvertera Books-objektet till JSON och logga det
            String jsonPayloadStr = mapper.writeValueAsString(book);
            System.out.println("Skickar följande payload till POST: " + jsonPayloadStr);

            // Koppla JSON till POST-förfrågan
            StringEntity jsonPayload = new StringEntity(jsonPayloadStr, ContentType.APPLICATION_JSON);
            postRequest.setEntity(jsonPayload);

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                int statusCode = response.getCode();
                if (statusCode == 200 || statusCode == 201) {
                    // Packa upp svaret och skriv ut resultatet
                    String jsonResp = EntityUtils.toString(response.getEntity());
                    Books respBook = mapper.readValue(jsonResp, Books.class);
                    System.out.println("Bok sparad: " + respBook.getTitle() + " med id " + respBook.getId());
                } else {
                    String errorResponse = EntityUtils.toString(response.getEntity());
                    System.out.println("Fel! Statuskod: " + statusCode + " vid POST-förfrågan till: " + uri);
                    System.out.println("Felmeddelande från servern: " + errorResponse);
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("Fel vid konvertering av Books-objekt till JSON: " + e.getMessage());
        }
    }

    // Skicka en PUT-förfrågan för att uppdatera en bok
    public static void sendPutBookRequest(String uri, Books book) throws IOException, ParseException {
        if (book == null) {
            System.out.println("Boken är null och kan inte uppdateras.");
            return;
        }

        HttpPut putRequest = new HttpPut(uri);

        try {
            // Konvertera Books-objektet till JSON och logga det
            String jsonPayloadStr = mapper.writeValueAsString(book);
            System.out.println("Skickar följande payload till PUT: " + jsonPayloadStr);

            // Koppla JSON till PUT-förfrågan
            StringEntity jsonPayload = new StringEntity(jsonPayloadStr, ContentType.APPLICATION_JSON);
            putRequest.setEntity(jsonPayload);

            try (CloseableHttpResponse response = httpClient.execute(putRequest)) {
                int statusCode = response.getCode();
                if (statusCode == 200 || statusCode == 204) {
                    // Packa upp svaret och skriv ut resultatet
                    String jsonResp = EntityUtils.toString(response.getEntity());
                    Books updatedBook = mapper.readValue(jsonResp, Books.class);
                    System.out.println("Bok uppdaterad: " + updatedBook.getTitle() + " med id " + updatedBook.getId());
                } else {
                    String errorResponse = EntityUtils.toString(response.getEntity());
                    System.out.println("Fel! Statuskod: " + statusCode + " vid PUT-förfrågan till: " + uri);
                    System.out.println("Felmeddelande från servern: " + errorResponse);
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("Fel vid konvertering av Books-objekt till JSON: " + e.getMessage());
        }
    }

    // Skicka en DELETE-förfrågan för att ta bort en bok
    public static void sendDeleteBookRequest(String uri) throws IOException, ParseException {
        HttpDelete deleteRequest = new HttpDelete(uri);

        try (CloseableHttpResponse response = httpClient.execute(deleteRequest)) {
            if (response.getCode() == 200 || response.getCode() == 204) {
                System.out.println("Bok med id " + uri.substring(uri.lastIndexOf("/") + 1) + " borttagen.");
            } else {
                String errorResponse = EntityUtils.toString(response.getEntity());
                System.out.println("Fel! Statuskod: " + response.getCode() + " vid DELETE-förfrågan till: " + uri);
                System.out.println("Felmeddelande från servern: " + errorResponse);
            }
        }
    }
}
