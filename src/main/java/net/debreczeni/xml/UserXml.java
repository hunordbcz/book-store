package net.debreczeni.xml;

import net.debreczeni.model.User;
import net.debreczeni.model.list.ModelList;
import net.debreczeni.model.list.UserList;

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

public class UserXml implements XmlHandler<User> {
    private static final AtomicInteger currentID = new AtomicInteger();

    private final JAXBContext jaxbContext;
    private final Marshaller marshaller;
    private final String filename;

    public UserXml(String filename) throws JAXBException, IOException {
        this.filename = filename;

        jaxbContext = JAXBContext.newInstance(UserList.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        setCurrentID();
    }

    @Override
    public void setCurrentID() throws IOException, JAXBException {
        if (currentID.get() == -1) {
            return;
        }

        currentID.set(get().stream().map(User::getId).max(Comparator.naturalOrder()).orElse(0));
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
    public synchronized void create(User user) throws JAXBException, IOException {
        final List<User> users = get();
        user.setId(getNewID());
        users.add(user);

        set(users);
    }

    @Override
    public synchronized List<User> get() throws JAXBException, IOException {
        final BufferedReader reader = getReader();

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ModelList<User> list = (UserList) unmarshaller.unmarshal(reader);

        reader.close();
        return Optional.ofNullable(list.getList()).orElse(new ArrayList<>());
    }

    @Override
    public synchronized void set(List<User> list) throws JAXBException, IOException {
        list.stream().filter(user -> user.getId() == null).forEach(user -> user.setId(getNewID()));

        final BufferedWriter writer = getWriter();
        marshaller.marshal(new UserList(list), writer);
        writer.flush();
        writer.close();
    }

    @Override
    public synchronized void update(User obj) throws JAXBException, IOException {
        final List<User> list = get();
        list.remove(obj); //is checked with ID
        list.add(obj);

        set(list);
    }

    @Override
    public synchronized void delete(int id) throws JAXBException, IOException {
        set(get().stream().filter(o -> o.getId() != id).collect(Collectors.toList()));
    }
}
