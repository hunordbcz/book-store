package net.debreczeni.controller;

import net.debreczeni.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookController {
    public List<Book> getAll() {
        Book book1 = new Book(
                "Title1",
                "Author1",
                "Genre1",
                12L,
                10D
        );
        Book book2 = new Book(
                "Clean Code2",
                "Joking Rowling2",
                "Comedy2",
                12L,
                10D
        );
        Book book3 = new Book(
                "asd3",
                "tes3",
                "ghj3",
                12L,
                10D
        );
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        return books;
    }
}
