package net.debreczeni.xml;

import net.debreczeni.model.User;
import net.debreczeni.model.list.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Map;

public class UserXml implements XmlHandler<User> {
    private final JAXBContext jaxbContext;
    private final Marshaller marshaller;
    private final String filename;

    public UserXml(String filename) throws JAXBException, IOException {
        this.filename = filename;


        jaxbContext = JAXBContext.newInstance(Users.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    @Override
    public BufferedReader getReader() throws FileNotFoundException {
        final File f = new File(filename);
        return new BufferedReader(new FileReader(f));
    }

    @Override
    public BufferedWriter getWriter() throws IOException {
        final File f = new File(filename);
        return new BufferedWriter(new FileWriter(f));
    }

    @Override
    public void create(User obj) throws JAXBException, IOException {
        final Map<Integer,User> users = get();
        users.put(obj.getId(), obj);

        final BufferedWriter writer = getWriter();
        marshaller.marshal(new Users(users), writer);
        writer.flush();
        writer.close();
    }

    @Override
    public Map<Integer, User> get() throws JAXBException, IOException {
        final BufferedReader reader = getReader();

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Users outCustomer = (Users) unmarshaller.unmarshal(reader);

        reader.close();
        return outCustomer.getUsers();
    }

    @Override
    public void set(Object list) throws JAXBException, IOException {
        if (!(list instanceof Users)) {
            return;
        }

        final BufferedWriter writer = getWriter();
        marshaller.marshal(list, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public void update(int id, User obj) throws JAXBException, IOException {
//        final List<User> users = get();
//        users.add(obj);
//
//        final BufferedWriter writer = getWriter();
//        marshaller.marshal(new Users(users), writer);
//        writer.flush();
//        writer.close();
    }

    @Override
    public void delete(int id) {

    }
}
