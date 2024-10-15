package AWSklientAS.Models;

import java.util.Objects;

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

    // Överskrid equals och hashcode för att jämföra objekt korrekt
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && age == author.age && name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    // Överskrid toString för att skriva ut författarinformation lättare
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
