package AWSklientAS;

import AWSklientAS.Models.Author;
import AWSklientAS.Models.Books;
import AWSklientAS.ServiceManager.ServiceManager;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Välj mellan localhost eller AWS
        System.out.println("Välj miljö: 1 för Localhost, 2 för AWS (Elastic Beanstalk): ");
        String choice = scanner.nextLine();
        String baseUrl;

        if ("1".equals(choice)) {
            // Localhost
            baseUrl = "http://localhost:5000/books";
        } else if ("2".equals(choice)) {
            // AWS Elastic Beanstalk
            baseUrl = "http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books";
        } else {
            System.out.println("Ogiltigt val, avslutar programmet.");
            return;
        }

        try {
            // Skicka en GET-förfrågan
            System.out.println("Skickar GET-förfrågan till " + baseUrl);
            ServiceManager.sendGetRequest(baseUrl);

            // Exempel på POST-förfrågan
            Books newBook = new Books("Ny Bok", "ISBN123456");
            newBook.setAuthor(new Author("Författare Namn", 40));
            System.out.println("Skickar POST-förfrågan...");
            ServiceManager.sendPostBookRequest(baseUrl, newBook);

            // Exempel på PUT-förfrågan (uppdatera en bok med ID 1)
            newBook.setTitle("Uppdaterad Bok");
            System.out.println("Skickar PUT-förfrågan...");
            ServiceManager.sendPutBookRequest(baseUrl + "/1", newBook);

            // Exempel på DELETE-förfrågan (ta bort en bok med ID 1)
            System.out.println("Skickar DELETE-förfrågan...");
            ServiceManager.sendDeleteBookRequest(baseUrl + "/1");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}