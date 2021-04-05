package net.debreczeni.controller;

import net.debreczeni.exception.AuthException;
import net.debreczeni.model.User;
import net.debreczeni.view.BookManagement;
import net.debreczeni.view.BookStore;
import net.debreczeni.view.table.BookTableModel;
import net.debreczeni.view.table.CartTableModel;
import net.debreczeni.view.table.SearchableBookTableModel;

import javax.swing.*;

public class AuthController {

    private final BookController bookController = new BookController();

    public void authenticate(String username, char[] password) throws AuthException {
        if(username.equals("admin")){
            User user = new User();
            final JFrame homepage = new BookManagement(user);
            homepage.setVisible(true);
            return;
        }

        final SearchableBookTableModel bookList = new SearchableBookTableModel(bookController::getAll);
        final CartTableModel cartModel = new CartTableModel();

        if(username.equals("test")){
            User user = new User();
            final JFrame homepage = new BookStore(bookList, cartModel);
            homepage.setVisible(true);
            return;
        }

        throw new AuthException("Invalid login details");
    }
}
