package net.debreczeni.xml;

import net.debreczeni.model.User;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface XmlHandler<T> {

    BufferedReader getReader() throws FileNotFoundException;
    BufferedWriter getWriter() throws IOException;

    void create(T obj) throws JAXBException, IOException;
    Map<Integer, User> get() throws JAXBException, IOException;
    void set(Object list) throws JAXBException, IOException;
    void update(int id, T obj) throws JAXBException, IOException;
    void delete(int id);
}
