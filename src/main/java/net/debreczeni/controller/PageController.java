package net.debreczeni.controller;

import net.debreczeni.view.Login;

import javax.swing.*;

public class PageController {

    static {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private PageController() {
    }

    public static PageController getInstance() {
        return PageController.Singleton.INSTANCE;
    }

    public void showLogin() {
        final Login login = new Login();
        login.setVisible(true);
    }

    private static class Singleton {
        private static final PageController INSTANCE = new PageController();
    }
}
