package net.debreczeni.model;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
@XmlType(propOrder = {"title", "author", "genre", "quantity", "price"})
public class Book {
    private String title;
    private String author;
    private String genre;
    private Long quantity;
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, genre, price);
    }
}
