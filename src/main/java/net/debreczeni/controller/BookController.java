package net.debreczeni.controller;

import net.debreczeni.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookController {
    public List<Book> getAll() {
        Book book1 = new Book(
                "Title",
                "Author",
                "Genre",
                12L,
                10D
        );
        Book book2 = new Book(
                "Clean Code",
                "Joking Rowling",
                "Comedy",
                12L,
                10D
        );
        Book book3 = new Book(
                "asd",
                "tes",
                "ghj",
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
