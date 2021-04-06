package net.debreczeni.controller;

import net.debreczeni.exception.AuthException;
import net.debreczeni.model.User;
import net.debreczeni.view.BookManagement;
import net.debreczeni.view.BookStore;

import javax.swing.*;

public class AuthController {

    private final BookController bookController = BookController.getInstance();

    private AuthController() {
    }

    public static AuthController getInstance() {
        return AuthController.Singleton.INSTANCE;
    }

    public void authenticate(String username, char[] password) throws AuthException {
        if (username.equals("admin")) {
            User user = new User();
            final JFrame homepage = new BookManagement(user);
            homepage.setVisible(true);
            return;
        }

        if (username.equals("test")) {
            User user = new User();
            final JFrame homepage = new BookStore(bookController.getSearchableBookTableModel(), bookController.getCartTableModel());
            homepage.setVisible(true);
            return;
        }

        throw new AuthException("Invalid login details");
    }

    private static class Singleton {
        private static final AuthController INSTANCE = new AuthController();
    }
}
