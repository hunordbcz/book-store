package net.debreczeni;

import net.debreczeni.controller.PageController;
import net.debreczeni.model.AccessType;
import net.debreczeni.model.User;
import net.debreczeni.model.list.Users;
import net.debreczeni.xml.UserXml;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException, InterruptedException {
//        PageController.getInstance().showLogin();

        User user = new User();
        user.setUsername("username");
        user.setPassword("testPass");
        user.setAccessType(AccessType.MANAGER);
        Map<Integer, User> usersH = new HashMap<>();
        usersH.put(1,user);
        usersH.put(2,user);
        usersH.put(3,user);
        Users users = new Users(usersH);
        UserXml userXml = new UserXml("users.xml");
        userXml.set(users);

        Thread.sleep(1000);

        /*User user = new User();
        user.setUsername("username");
        user.setPassword("testPass");
        user.setAccessType(AccessType.MANAGER);

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        StringWriter xmlWriter = new StringWriter();
        marshaller.marshal(user, xmlWriter);
        System.out.println(xmlWriter.toString());


        // Step 3 - Convert XML back to Domain Model
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader xmlReader = new StringReader(xmlWriter.toString());
        User outCustomer = (User) unmarshaller.unmarshal(xmlReader);

        System.out.println(outCustomer);*/
    }
}
