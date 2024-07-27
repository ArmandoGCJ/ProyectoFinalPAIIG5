package ec.edu.uce.AdminOrlando.view;

import javax.swing.*;
import java.awt.*;

public class AddOrders extends JDialog {
    private JTextField productNameField;
    private JButton addButton;
    private OrdersFrame ordersFrame;

    public AddOrders(OrdersFrame ordersFrame) {
        this.ordersFrame = ordersFrame;

        setTitle("Agregar Producto");
        setSize(300, 200);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(ordersFrame);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        productNameLabel.setBounds(10, 20, 150, 25);
        panel.add(productNameLabel);

        productNameField = new JTextField(20);
        productNameField.setBounds(170, 20, 100, 25);
        panel.add(productNameField);

        addButton = new JButton("Agregar");
        addButton.setBounds(10, 60, 260, 35); // Botón más largo
        addButton.setBackground(new Color(246, 246, 246));
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(e -> {
            String productName = productNameField.getText();
            if (!productName.isEmpty()) {
                ordersFrame.addProduct(productName);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}