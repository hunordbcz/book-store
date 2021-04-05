package net.debreczeni;

import net.debreczeni.controller.HomeController;

public class Main {
    public static void main(String[] args) {
        new HomeController().showLogin();

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
