package net.debreczeni.xml;

import net.debreczeni.model.Book;
import net.debreczeni.model.list.BookList;
import net.debreczeni.model.list.ModelList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BookXml implements XmlHandler<Book> {
    private static final AtomicInteger currentID = new AtomicInteger(-1);

    private final JAXBContext jaxbContext;
    private final Marshaller marshaller;
    private final String filename;

    public BookXml(String filename) throws JAXBException, IOException {
        this.filename = filename;

        jaxbContext = JAXBContext.newInstance(BookList.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        setCurrentID();
    }

    @Override
    public void setCurrentID() throws IOException, JAXBException {
        if (currentID.get() == -1) {
            return;
        }

        currentID.set(get().stream().map(Book::getId).max(Comparator.naturalOrder()).orElse(0));
    }

    @Override
    public int getNewID() {
        return currentID.incrementAndGet();
    }

    @Override
    public synchronized BufferedReader getReader() throws FileNotFoundException {
        final File f = new File(filename);
        return new BufferedReader(new FileReader(f));
    }

    @Override
    public synchronized BufferedWriter getWriter() throws IOException {
        final File f = new File(filename);
        return new BufferedWriter(new FileWriter(f));
    }

    @Override
    public synchronized void create(Book book) throws JAXBException, IOException {
        final List<Book> books = get();
        book.setId(getNewID());
        books.add(book);

        set(books);
    }

    @Override
    public synchronized List<Book> get() throws JAXBException, IOException {
        final BufferedReader reader = getReader();

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ModelList<Book> list = (BookList) unmarshaller.unmarshal(reader);

        reader.close();
        return Optional.ofNullable(list.getList()).orElse(new ArrayList<>());
    }

    @Override
    public synchronized void set(List<Book> list) throws JAXBException, IOException {
        list.stream().filter(book -> book.getId() == null).forEach(book -> book.setId(getNewID()));

        final BufferedWriter writer = getWriter();
        marshaller.marshal(new BookList(list), writer);
        writer.flush();
        writer.close();
    }

    @Override
    public synchronized void update(Book obj) throws JAXBException, IOException {
        final List<Book> list = get();
        list.remove(obj); //is checked with ID
        list.add(obj);

        set(list);
    }

    @Override
    public synchronized void delete(int id) throws JAXBException, IOException {
        set(get().stream().filter(o -> o.getId() != id).collect(Collectors.toList()));
    }
}
