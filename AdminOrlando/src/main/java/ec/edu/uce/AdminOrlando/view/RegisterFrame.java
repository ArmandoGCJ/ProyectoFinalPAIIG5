package ec.edu.uce.AdminOrlando.view;

import ec.edu.uce.AdminOrlando.Api.ClienteApi;
import ec.edu.uce.AdminOrlando.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RegisterFrame extends JFrame {
    private JTextField registerUsernameField;
    private JTextField registerPasswordField;
    private JButton registerButton;
    private JButton goToLoginButton;
    private LoginFrame loginFrame;
    private ClienteApi clienteApi;

    public RegisterFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        setTitle("Registrarse");
        setResizable(false);
        setSize(400, 200); // Tamaño de la ventana aumentado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.clienteApi = new ClienteApi();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(216, 20, 47));

        JLabel registerUserLabel = new JLabel("Nombre de usuario:");
        registerUserLabel.setBounds(10, 20, 140, 25);
        panel.add(registerUserLabel);

        registerUsernameField = new JTextField(20);
        registerUsernameField.setBounds(140, 20, 230, 25);
        panel.add(registerUsernameField);

        JLabel registerPasswordLabel = new JLabel("Contraseña:");
        registerPasswordLabel.setBounds(10, 60, 100, 25);
        panel.add(registerPasswordLabel);

        registerPasswordField = new JTextField(20);
        registerPasswordField.setBounds(140, 60, 230, 25);
        panel.add(registerPasswordField);

        registerButton = new JButton("Registrar");
        registerButton.setBounds(15, 100, 130, 35);
        registerButton.setBackground(new Color(246, 246, 246));
        panel.add(registerButton);

        goToLoginButton = new JButton("Atras para iniciar sesion");
        goToLoginButton.setBounds(170, 100, 190, 35);
        goToLoginButton.setBackground(new Color(246, 246, 246));
        panel.add(goToLoginButton);

        add(panel);


        registerButton.addActionListener(e -> {
            String usuario = registerUsernameField.getText();
            String password = registerPasswordField.getText();
            if (!usuario.isEmpty() && !password.isEmpty()) {
                registrarCliente(usuario, password);
                JOptionPane.showMessageDialog(this, "Registrado con éxito");
            }


            this.setVisible(false);
            loginFrame.setVisible(true);
        });

        goToLoginButton.addActionListener(e -> {
            this.setVisible(false);
            loginFrame.setVisible(true);
        });
    }

    public void registrarCliente(String nombre, String contrasena) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setContrasena(contrasena);
        cliente.setRol("ADMIN");
        try {
            Cliente nuevoCliente = clienteApi.registrarCliente(cliente);
            System.out.println("Cliente registrado: " + nuevoCliente.getNombre());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}