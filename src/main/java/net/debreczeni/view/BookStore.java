package net.debreczeni.view;

import net.debreczeni.model.User;

import javax.swing.*;
import java.awt.*;

public class BookStore extends JFrame{
    private JPanel mainPanel;
    private JButton sellButton;
    private JButton managerSectionButton;
    private JTable bookTable;

    public BookStore(Component relativeTo, User user) throws HeadlessException {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(relativeTo);
        this.pack();
    }
}
