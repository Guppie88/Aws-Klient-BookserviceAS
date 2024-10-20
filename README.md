# Aws-Klient-BookserviceAS

**Aws-Klient-BookserviceAS** är en enkel klientapplikation skriven i Java för att anropa ett API som hanterar böcker. Klienten kan skicka GET-, POST-, PATCH- och DELETE-förfrågningar till API som körs både lokalt och på AWS Elastic Beanstalk. Den använder Apache HttpClient och Jackson för att hantera HTTP-förfrågningar och JSON-respons.

## Funktioner

- **GET-förfrågningar**: Hämta alla böcker från API.
- **POST-förfrågningar**: Lägg till en ny bok i API.
- **PATCH-förfrågningar**: Uppdatera en befintlig bok i API.
- **DELETE-förfrågningar**: Ta bort en bok från API.

## Teknologier

- **Java 21**: Grundspråk för klienten.
- **Apache HttpClient**: För att hantera HTTP-förfrågningar.
- **Jackson**: För att hantera JSON-konvertering.
- **Maven**: Byggverktyg och beroendehantering.

## Installation och användning

1. **Klona projektet**  
   För att klona projektet från GitHub, kör följande kommando:

   ```bash
   git clone https://github.com/Guppie88/Aws-Klient-BookserviceAS.git
   ```

2. **Bygg projektet**  
   Navigera till projektmappen och bygg projektet genom att köra:

   ```bash
   mvn clean install
   ```

3. **Kör applikationen**  
   Applikationen kan köras genom att köra huvudklassen `Main`:

   ```bash
   mvn exec:java -Dexec.mainClass="AWSklientAS.Main"
   ```

## Konfiguration

Applikationen är förkonfigurerad för att kunna skicka GET-, POST-, PATCH- och DELETE-förfrågningar till både en lokal instans av API och en version som körs på AWS Elastic Beanstalk.

### Lokal miljö:
- **URL**: `http://localhost:5000/books`

### AWS Elastic Beanstalk:
- **URL**: `http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books`

## Exempel på användning

### GET-förfrågan (Hämta alla böcker)
```java
ServiceManager.sendGetRequest("http://localhost:5000/books");
// eller
ServiceManager.sendGetRequest("http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books");
```

### POST-förfrågan (Lägg till en ny bok)
```java
Books newBook = new Books("Titel på boken", "ISBN12345");
Author author = new Author("Författarens namn", 45);
newBook.setAuthor(author);
ServiceManager.sendPostBookRequest("http://localhost:5000/books", newBook);
```

### PATCH-förfrågan (Uppdatera en bok)
```java
Books updatedBook = new Books("Uppdaterad titel på boken", "ISBN54321");
Author updatedAuthor = new Author("Uppdaterad författare", 50);
updatedBook.setAuthor(updatedAuthor);
ServiceManager.sendPutBookRequest("http://localhost:5000/books/1", updatedBook);
```

### DELETE-förfrågan (Ta bort en bok)
```java
ServiceManager.sendDeleteBookRequest("http://localhost:5000/books/1");
```

## Framtida utveckling

- Utveckla användargränssnitt för att enkelt skicka förfrågningar.


