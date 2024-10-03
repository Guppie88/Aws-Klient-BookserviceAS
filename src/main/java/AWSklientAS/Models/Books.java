package AWSklientAS.Models;

// Books-klass representerar en bok
public class Books {
    private long id;
    private String title;
    private String ISBN;
    private Author author; // Länk till författare

    // Standardkonstruktor
    public Books() {
    }

    // Konstruktor med fält
    public Books(String title, String ISBN) {
        this.title = title;
        this.ISBN = ISBN;
    }

    // Getters och setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
