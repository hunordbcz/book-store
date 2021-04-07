package net.debreczeni.view;

import net.debreczeni.controller.UserController;
import net.debreczeni.exception.AuthException;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private static final UserController userController = UserController.getInstance();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JPanel mainPanel;

    public Login() throws HeadlessException {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        enterButton.addActionListener(e -> {
            try {
                userController.authenticate(usernameField.getText(), passwordField.getText());
                this.dispose();
            } catch (AuthException authException) {
                JOptionPane.showMessageDialog(
                        this,
                        authException.getMessage(),
                        "Authentication Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
