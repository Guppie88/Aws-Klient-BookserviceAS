# AWSklientAS

**AWSklientAS** är en Java-applikation som hanterar HTTP-förfrågningar för att interagera med ett bokhanterings-API. Den är byggd med hjälp av **Apache HttpClient** för att göra GET- och POST-förfrågningar, och **Jackson** används för att serialisera och deserialisera JSON-data. Applikationen kan anslutas till både en lokal server och en server som körs på AWS Elastic Beanstalk.

## Funktioner

- **GET-förfrågningar**: Hämtar data från ett API och presenterar det i konsolen.
- **POST-förfrågningar**: Skickar data (t.ex. böcker) till ett API.
- **Modellklasser**: Applikationen använder Java-modellklasser (`Author` och `Books`) för att representera objekt som skickas till och tas emot från API:et.

## Teknologier och Beroenden

- **Java 21**: Den senaste versionen av Java används för att utveckla applikationen.
- **Apache HttpClient 5**: Ett bibliotek för att skicka HTTP-förfrågningar.
- **Jackson-databind**: För att hantera JSON-serialisering och deserialisering av Java-objekt.
- **SLF4J**: Ett enkelt loggningsramverk för att hantera applikationsloggar.

## Installation och Användning

### Förutsättningar

- **Java 21**: Se till att du har Java 21 installerat på din dator.
- **Maven**: Används för att hantera projektets beroenden och bygga applikationen.
- **Git**: För att klona projektet från GitHub.

### Steg för att köra applikationen lokalt

1. **Klona projektet från GitHub**:
    ```bash
    git clone https://github.com/Guppie88/AWSklientAS.git
    cd AWSklientAS
    ```

2. **Bygg projektet med Maven**:
    ```bash
    mvn clean install
    ```

3. **Kör applikationen**:
    Applikationen kan köras med standardinställningarna för att skicka GET- och POST-förfrågningar till en server som körs på `localhost:5000` eller till Elastic Beanstalk på AWS.

    Exempel:
    ```bash
    mvn exec:java -Dexec.mainClass="AWSklientAS.Main"
    ```

4. **Testa GET- och POST-förfrågningar**:
    Applikationen gör GET- och POST-förfrågningar till ett API. Se till att du har en server igång på `http://localhost:5000` eller att du ansluter till din Elastic Beanstalk-instans.

    - **Lokal URL**: 
      - `http://localhost:5000/books`
    - **Elastic Beanstalk URL**: 
      - `http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books`

### Exempel på GET- och POST-förfrågningar

- **GET-förfrågan**: Hämtar en lista med böcker från API:et och skriver ut titeln och författarens namn i konsolen.
    ```java
    ServiceManager.sendGetRequest("http://localhost:5000/books");
    // eller för Elastic Beanstalk:
    ServiceManager.sendGetRequest("http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books");
    ```

- **POST-förfrågan**: Skickar en bok och författarinformation till API:et och sparar det.
    ```java
    Books newBook = new Books("Titel på boken", "ISBN12345");
    Author author = new Author("Författarens namn", 45);
    newBook.setAuthor(author);
    ServiceManager.sendPostBookRequest("http://localhost:5000/books", newBook);
    // eller för Elastic Beanstalk:
    ServiceManager.sendPostBookRequest("http://bookservice-env.eba-bezef5r2.eu-north-1.elasticbeanstalk.com/books", newBook);
    ```

### Förberedelse för AWS-distribution

Applikationen är förberedd för att kunna distribueras på en AWS-instans, som t.ex. en EC2-instans eller Elastic Beanstalk. När applikationen distribueras kan du byta ut `localhost:5000` mot den publika DNS-adressen eller IP-adressen för din AWS Elastic Beanstalk-instans.

## Felsökning

### Connection refused

Om du stöter på ett felmeddelande som indikerar "Connection refused", se till att du har en server som körs på den angivna porten, t.ex. `localhost:5000`. Du kan enkelt sätta upp en server med t.ex. Spring Boot eller Flask för att ta emot och svara på GET- och POST-förfrågningar.

## Beroenden

Projektets beroenden hanteras av Maven och finns i `pom.xml`-filen. De inkluderar bland annat:
- `Apache HttpClient`
- `Jackson-databind`
- `SLF4J`
- `JUnit 5` (valfritt för tester)

## Licens

Detta projekt är fritt att använda och modifiera. Inga specifika licensregler är angivna för närvarande.

## Kontakt

Om du har några frågor eller förslag, kontakta mig via GitHub: [Guppie88](https://github.com/Guppie88).
