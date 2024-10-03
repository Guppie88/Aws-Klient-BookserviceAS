package AWSklientAS.Models;

// Author-klass representerar en författare
public class Author {
    private long id;
    private String name;
    private int age;

    // Standardkonstruktor
    public Author() {
    }

    // Konstruktor med fält
    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters och setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
