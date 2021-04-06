package net.debreczeni.controller;

import net.debreczeni.view.Login;

import javax.swing.*;

public class HomeController {

    static {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private HomeController() {
    }

    public static HomeController getInstance() {
        return HomeController.Singleton.INSTANCE;
    }

    public void showLogin() {
        final Login login = new Login();
        login.setVisible(true);
    }

    private static class Singleton {
        private static final HomeController INSTANCE = new HomeController();
    }
}
