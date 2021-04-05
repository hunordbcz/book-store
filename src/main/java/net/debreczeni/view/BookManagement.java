package net.debreczeni.view;

import net.debreczeni.model.User;

import javax.swing.*;
import java.awt.*;

public class BookManagement extends JFrame{
    private JPanel mainPanel;
    private final User user;

    public BookManagement(User user) throws HeadlessException {
        this.user = user;
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
    }
}
