package ec.edu.uce.FabricaOrlando.view;

import ec.edu.uce.FabricaOrlando.model.Client;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterFrame() {
        setTitle("Register");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 80, 100, 25);
        panel.add(registerButton);

        add(panel);

        // Acciones de botones
        registerButton.addActionListener(e-> {

            String name = String.valueOf(usernameField);
            String password = String.valueOf(passwordField);
            Client client = new Client();
            client.setUserName(name);
            client.setPassword(password);
        });
    }
}