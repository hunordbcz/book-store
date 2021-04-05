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

    public void showLogin(){
        final Login login = new Login();
        login.setVisible(true);
    }
}
