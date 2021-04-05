package net.debreczeni.model;

import java.util.ArrayList;
import java.util.List;


public class Report {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }
    public void addAllBooks(List<Book> books){
        this.books.addAll(books);
    }

    @Override
    public String toString() {
        return "Report{" +
                "books=" + books +
                '}';
    }
}
