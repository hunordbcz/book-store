package net.debreczeni.view;

import net.debreczeni.controller.AuthController;
import net.debreczeni.exception.AuthException;
import net.debreczeni.model.Manager;
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
        this.authController = new AuthController();

        enterButton.addActionListener(e -> {
            try {
                final User user = authController.authenticate(usernameField.getText(), passwordField.getPassword());

                final JFrame homepage;
                if (user instanceof Manager) {
                    homepage = new BookManagement(this, (Manager) user);
                } else {
                    homepage = new BookStore(this, user);
                }

                homepage.setVisible(true);
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
