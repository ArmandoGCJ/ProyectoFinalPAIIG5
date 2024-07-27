package ec.edu.uce.AdminOrlando.view;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField registerUsernameField;
    private JTextField registerPasswordField;
    private JButton registerButton;
    private JButton goToLoginButton;
    private LoginFrame loginFrame;

    public RegisterFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        setTitle("Registrarse");
        setResizable(false);
        setSize(400, 200); // Tamaño de la ventana aumentado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        // Acciones de botones
        registerButton.addActionListener(e -> {
            // Aquí debería ir la lógica de registro
            JOptionPane.showMessageDialog(this, "Registrado con éxito");
            this.setVisible(false);
            loginFrame.setVisible(true);
        });

        goToLoginButton.addActionListener(e -> {
            this.setVisible(false);
            loginFrame.setVisible(true);
        });
    }
}