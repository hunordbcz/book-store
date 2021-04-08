package net.debreczeni.model;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Book {
    private Integer id;
    private String title;
    private String author;
    private String genre;
    private Long quantity;
    private Double price;

    public Book(String title, String author, String genre, Long quantity, Double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
