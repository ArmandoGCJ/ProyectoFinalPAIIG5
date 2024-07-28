package ec.edu.uce.FabricaOrlando.view;

import ec.edu.uce.FabricaOrlando.Controller.Container;
import ec.edu.uce.FabricaOrlando.model.Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AuthFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Login components
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JButton loginButton;
    private JButton goToRegisterButton;

    // Register components
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JButton registerButton;
    private JButton goToLoginButton;

    private Container container;

    public AuthFrame() {
        setTitle("Authentication");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        container = new Container();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize Login Panel
        JPanel loginPanel = new JPanel(null);

        JLabel loginUserLabel = new JLabel("Username:");
        loginUserLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(loginUserLabel);

        loginUsernameField = new JTextField(20);
        loginUsernameField.setBounds(100, 20, 165, 25);
        loginPanel.add(loginUsernameField);

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(loginPasswordLabel);

        loginPasswordField = new JPasswordField(20);
        loginPasswordField.setBounds(100, 50, 165, 25);
        loginPanel.add(loginPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginPanel.add(loginButton);

        goToRegisterButton = new JButton("Register");
        goToRegisterButton.setBounds(180, 80, 100, 25);
        loginPanel.add(goToRegisterButton);

        // Initialize Register Panel
        JPanel registerPanel = new JPanel(null);

        JLabel registerUserLabel = new JLabel("Username:");
        registerUserLabel.setBounds(10, 20, 80, 25);
        registerPanel.add(registerUserLabel);

        registerUsernameField = new JTextField(20);
        registerUsernameField.setBounds(100, 20, 165, 25);
        registerPanel.add(registerUsernameField);

        JLabel registerPasswordLabel = new JLabel("Password:");
        registerPasswordLabel.setBounds(10, 50, 80, 25);
        registerPanel.add(registerPasswordLabel);

        registerPasswordField = new JPasswordField(20);
        registerPasswordField.setBounds(100, 50, 165, 25);
        registerPanel.add(registerPasswordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 100, 25);
        registerPanel.add(registerButton);

        goToLoginButton = new JButton("Login");
        goToLoginButton.setBounds(180, 80, 100, 25);
        registerPanel.add(goToLoginButton);

        // Add panels to CardLayout
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registerPanel, "Register");

        add(mainPanel);

        // Set initial panel
        cardLayout.show(mainPanel, "Login");

        // Action listeners
        loginButton.addActionListener(e -> {
            String name = loginUsernameField.getText();
            String password = new String(loginPasswordField.getPassword());

            try {
                container.iniciarSesion(name, password);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            new ProductFrame().setVisible(true);
            dispose();
        });

        goToRegisterButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        goToLoginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        registerButton.addActionListener(e -> {
            String name = registerUsernameField.getText();
            String password = new String(registerPasswordField.getPassword());
            Client client = new Client();
            client.getId();
            client.setNombre(name);
            client.setContrasena(password);
            client.setRol("Cliente");
            try {
                container.registrarCliente(client);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}