package net.debreczeni.view;

import net.debreczeni.model.Manager;
import net.debreczeni.model.User;

import javax.swing.*;
import java.awt.*;

public class BookManagement extends JFrame{
    private JPanel mainPanel;
    private final Manager manager;

    public BookManagement(Component relativeTo, Manager manager) throws HeadlessException {
        this.manager = manager;
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(relativeTo);
        this.pack();
    }
}
