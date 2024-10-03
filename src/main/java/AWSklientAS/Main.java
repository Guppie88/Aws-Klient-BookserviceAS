package AWSklientAS;

import AWSklientAS.ServiceManager.ServiceManager;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Använd localhost med port 8080
            ServiceManager.sendGetRequest("http://localhost:8080/api/books");

            // Om du senare vill skicka POST-request kan du också ändra denna till localhost:8080
            // ServiceManager.sendPostBookRequest("http://localhost:8080/api/books", myBook);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
