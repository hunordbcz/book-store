package net.debreczeni.xml;

import net.debreczeni.model.Book;
import net.debreczeni.model.User;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class BookXml implements XmlHandler<Book>{
    @Override
    public BufferedReader getReader() throws FileNotFoundException {
        return null;
    }

    @Override
    public BufferedWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public void create(Book obj) {

    }

    @Override
    public Map<Integer, User> get() {
        return null;
    }

    @Override
    public void set(Object list) throws JAXBException, IOException {

    }

    @Override
    public void update(int id, Book obj) {

    }

    @Override
    public void delete(int id) {

    }
}
