package net.debreczeni.controller;

import net.debreczeni.exception.AuthException;
import net.debreczeni.model.AccessType;
import net.debreczeni.model.User;
import net.debreczeni.model.table.UserTableModel;
import net.debreczeni.service.UserService;
import net.debreczeni.view.BookManagement;
import net.debreczeni.view.BookStore;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class UserController {
    private final UserService userService = UserService.getInstance();
    private final BookController bookController = BookController.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        return UserController.Singleton.INSTANCE;
    }

    public void authenticate(String username, String password) throws AuthException {
        final Optional<User> optionalUser = getAll().stream().filter(user ->
                user.getUsername().equals(username) && user.getPassword().equals(password)
        ).findAny();

        showHome(optionalUser.orElseThrow(() -> new AuthException("Invalid login details")));
    }

    public void showHome(User user) {
        JFrame homepage;
        if (user.getAccessType() == AccessType.MANAGER) {
            homepage = new BookManagement(user, bookController.getManageableBookTableModel(), getUserTableModel());
        } else {
            homepage = new BookStore(user, bookController.getSearchableBookTableModel(), bookController.getCartTableModel());
        }

        homepage.setVisible(true);
    }

    public UserTableModel getUserTableModel() {
        return new UserTableModel(this::getAll);
    }

    public List<User> getAll() {
        return UserService.getInstance().getAll();
    }

    public void removeUser(User user) {
        userService.delete(user.getId());
    }

    public void update(User user) {
        userService.update(user);
    }

    private static class Singleton {
        private static final UserController INSTANCE = new UserController();
    }
}
