package net.debreczeni.service;

import lombok.SneakyThrows;
import net.debreczeni.model.Book;
import net.debreczeni.xml.BookXml;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService implements Service<Book> {
    private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private final BookXml bookXml;

    @SneakyThrows
    private BookService() {
        bookXml = new BookXml("src/main/resources/books.xml");
    }

    public static BookService getInstance() {
        return BookService.Singleton.INSTANCE;
    }

    @Override
    public void create(Book obj) {
        try {
            bookXml.create(obj);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public List<Book> getAll() {
        try {
            return bookXml.get();
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Book obj) {
        try {
            bookXml.update(obj);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            bookXml.delete(id);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private static class Singleton {
        private static final BookService INSTANCE = new BookService();
    }
}
