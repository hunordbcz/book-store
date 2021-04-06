package net.debreczeni.view;

import net.debreczeni.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookManagement extends JFrame{
    private JPanel mainPanel;
    private JTable userTable;
    private JTable bookTable;
    private JButton logOutButton;
    private JTextField titleSearchField;
    private JTextField genreSearchField;
    private JTextField authorSearchField;
    private JButton removeButton;
    private JButton newButton;
    private JButton newButton1;
    private JButton removeButton1;
    private JButton reportsButton;
    private JLabel nameField;
    private final User user;

    public BookManagement(User user) throws HeadlessException {
        this.user = user;
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        logOutButton.addActionListener(e -> {

        });
    }
}
