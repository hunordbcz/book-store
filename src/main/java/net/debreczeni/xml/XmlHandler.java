package net.debreczeni.xml;

import net.debreczeni.model.Book;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface XmlHandler<T> {

    void setCurrentID() throws IOException, JAXBException;

    int getNewID();

    BufferedReader getReader() throws FileNotFoundException;

    BufferedWriter getWriter() throws IOException;

    void create(T obj) throws JAXBException, IOException;

    List<T> get() throws JAXBException, IOException;

    void set(List<T> list) throws JAXBException, IOException;

    void update(T obj) throws JAXBException, IOException;

    void delete(int id) throws JAXBException, IOException;
}
