package ec.edu.uce.AdminOrlando.view;

import ec.edu.uce.AdminOrlando.controller.AdminAppService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField; // Cambiado a JTextField para hacer visible la contraseña
    private JButton loginButton;
    private JButton goToRegisterButton;

    @Autowired
    private AdminAppService adminAppService;

    public LoginFrame() {
        setTitle("Login");
        setResizable(false);
        setSize(400,200); // Tamaño de la ventana aumenta
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(216, 20, 47));

        JLabel userLabel = new JLabel("Nombre de usuario:");
        userLabel.setBounds(10, 20, 140, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(140, 20, 230, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(10, 60, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JTextField(20); // Cambiado a JTextField para hacer visible la contraseña
        passwordField.setBounds(140, 60, 230, 25);
        panel.add(passwordField);

        loginButton = new JButton("Iniciar");
        loginButton.setBounds(10, 100, 150, 35); // Botón más largo
        loginButton.setBackground(new Color(246, 246, 246));
        panel.add(loginButton);

        goToRegisterButton = new JButton("Registrarse");
        goToRegisterButton.setCursor(new Cursor(6));
        goToRegisterButton.setBounds(220, 100, 150, 35); // Botón más largo
        goToRegisterButton.setBackground(new Color(246, 246, 246));
        panel.add(goToRegisterButton);

        add(panel);


        loginButton.addActionListener(e -> {

            new OrdersFrame().setVisible(true);
            dispose();
        });

        goToRegisterButton.addActionListener(e -> {
            this.setVisible(false);
            new RegisterFrame(this).setVisible(true);
        });
    }

}