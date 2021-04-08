package net.debreczeni.service;

import lombok.SneakyThrows;
import net.debreczeni.model.User;
import net.debreczeni.xml.UserXml;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements Service<User> {
    private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private final UserXml userXml;

    @SneakyThrows
    private UserService() {
        userXml = new UserXml("users.xml");
    }

    public static UserService getInstance() {
        return UserService.Singleton.INSTANCE;
    }

    @Override
    public void create(User obj) {
        try {
            userXml.create(obj);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        try {
            final List<User> users = userXml.get();
            return Optional.ofNullable(users).orElse(new ArrayList<>());
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(User obj) {
        try {
            userXml.update(obj);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            userXml.delete(id);
        } catch (JAXBException | IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private static class Singleton {
        private static final UserService INSTANCE = new UserService();
    }
}
