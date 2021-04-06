package net.debreczeni.view;

import net.debreczeni.controller.AuthController;
import net.debreczeni.exception.AuthException;
import net.debreczeni.model.AccessType;
import net.debreczeni.model.User;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private final AuthController authController;
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
        this.authController = AuthController.getInstance();

        enterButton.addActionListener(e -> {
            try {
                authController.authenticate(usernameField.getText(), passwordField.getPassword());
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
