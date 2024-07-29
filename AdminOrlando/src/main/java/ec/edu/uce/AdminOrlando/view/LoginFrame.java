package ec.edu.uce.AdminOrlando.view;

import ec.edu.uce.AdminOrlando.Api.ClienteApi;
import ec.edu.uce.AdminOrlando.Api.ProductoApi;
import ec.edu.uce.AdminOrlando.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton goToRegisterButton;
    private ClienteApi clienteApi;
    private ProductoApi productoApi;

    public LoginFrame() {
        setTitle("Login");
        setResizable(false);
        setSize(400, 200); // Tamaño de la ventana aumenta
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.clienteApi = new ClienteApi();
        this.productoApi = new ProductoApi();

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

        passwordField = new JTextField(20);
        passwordField.setBounds(140, 60, 230, 25);
        panel.add(passwordField);

        loginButton = new JButton("Iniciar");
        loginButton.setBounds(10, 100, 150, 35);
        loginButton.setBackground(new Color(246, 246, 246));
        panel.add(loginButton);

        goToRegisterButton = new JButton("Registrarse");
        goToRegisterButton.setCursor(new Cursor(6));
        goToRegisterButton.setBounds(220, 100, 150, 35);
        goToRegisterButton.setBackground(new Color(246, 246, 246));
        panel.add(goToRegisterButton);

        add(panel);

        loginButton.addActionListener(e -> {
            String usuario = usernameField.getText();
            String password = passwordField.getText();
            if (!usuario.isEmpty() && !password.isEmpty()) {
                if (iniciarSesion(usuario, password)) {
                    OrdersFrame ordersFrame = new OrdersFrame(new JPanel());
                    ordersFrame.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed");
                }
            }
        });

        goToRegisterButton.addActionListener(e -> {
            this.setVisible(false);
            new RegisterFrame(this).setVisible(true);
        });
    }

    public boolean iniciarSesion(String usuario, String password) {
        try {
            Cliente cliente = clienteApi.iniciarSesion(usuario, password);
            System.out.println("Cliente autenticado: " + cliente.getNombre());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}

