package AWSklientAS.ServiceManager;

import AWSklientAS.Models.Books;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
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
        HttpGet request = new HttpGet(uri); // Skapa en HTTP GET-förfrågan
        CloseableHttpResponse response = httpClient.execute(request); // Skicka GET-förfrågan

        if (response.getCode() != 200) {
            System.out.println("Fel! Statuskod: " + response.getCode());
            return;
        }

        // Packa upp svaret
        HttpEntity entity = response.getEntity();
        String jsonResp = EntityUtils.toString(entity);

        // Tolka JSON-svaret och skriv ut information om böcker
        ArrayList<Books> books = mapper.readValue(jsonResp, new TypeReference<>() {});
        books.forEach(book -> System.out.println(book.getTitle() + " skriven av " + book.getAuthor().getName()));
    }

    // Skicka en POST-förfrågan med en bok
    public static void sendPostBookRequest(String uri, Books book) throws IOException, ParseException {
        HttpPost postRequest = new HttpPost(uri); // Skapa en HTTP POST-förfrågan

        // Konvertera Books-objektet till JSON
        StringEntity jsonPayload = new StringEntity(mapper.writeValueAsString(book), ContentType.APPLICATION_JSON);
        postRequest.setEntity(jsonPayload); // Koppla JSON till POST-förfrågan

        CloseableHttpResponse response = httpClient.execute(postRequest); // Skicka POST-förfrågan

        if (response.getCode() != 200 && response.getCode() != 201) {
            System.out.println("Fel! Statuskod: " + response.getCode());
            return;
        }

        // Packa upp svaret och skriv ut resultatet
        String jsonResp = EntityUtils.toString(response.getEntity());
        Books respBook = mapper.readValue(jsonResp, Books.class);
        System.out.println("Bok sparad: " + respBook.getTitle() + " med id " + respBook.getId());
    }

    // Skicka en PUT-förfrågan för att uppdatera en bok
    public static void sendPutBookRequest(String uri, Books book) throws IOException, ParseException {
        HttpPut putRequest = new HttpPut(uri); // Skapa en HTTP PUT-förfrågan

        // Konvertera Books-objektet till JSON
        StringEntity jsonPayload = new StringEntity(mapper.writeValueAsString(book), ContentType.APPLICATION_JSON);
        putRequest.setEntity(jsonPayload); // Koppla JSON till PUT-förfrågan

        CloseableHttpResponse response = httpClient.execute(putRequest); // Skicka PUT-förfrågan

        if (response.getCode() != 200 && response.getCode() != 204) {
            System.out.println("Fel! Statuskod: " + response.getCode());
            return;
        }

        // Packa upp svaret och skriv ut resultatet
        String jsonResp = EntityUtils.toString(response.getEntity());
        Books updatedBook = mapper.readValue(jsonResp, Books.class);
        System.out.println("Bok uppdaterad: " + updatedBook.getTitle() + " med id " + updatedBook.getId());
    }

    // Skicka en DELETE-förfrågan för att ta bort en bok
    public static void sendDeleteBookRequest(String uri) throws IOException, ParseException {
        HttpDelete deleteRequest = new HttpDelete(uri);

        CloseableHttpResponse response = httpClient.execute(deleteRequest); // Skicka DELETE-förfrågan

        if (response.getCode() != 200 && response.getCode() != 204) {
            System.out.println("Fel! Statuskod: " + response.getCode());
            return;
        }

        System.out.println("Bok med id " + uri.substring(uri.lastIndexOf("/") + 1) + " borttagen.");
    }
}
