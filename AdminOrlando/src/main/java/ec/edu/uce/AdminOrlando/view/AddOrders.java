package ec.edu.uce.AdminOrlando.view;

import ec.edu.uce.AdminOrlando.model.Producto;

import javax.swing.*;
import java.awt.*;

public class AddOrders extends JDialog {
    private JTextField productNameField;
    private JTextField productDescriptionField;
    private JTextField productPriceField;
    private JButton addButton;
    private OrdersFrame ordersFrame;

    public AddOrders(OrdersFrame ordersFrame) {
        this.ordersFrame = ordersFrame;

        setTitle("Agregar Producto");
        setSize(400, 300);
        setResizable(true);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(ordersFrame);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(102, 205, 2));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(productNameLabel, gbc);

        productNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(productNameField, gbc);

        JLabel productDescriptionLabel = new JLabel("Descripción:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(productDescriptionLabel, gbc);

        productDescriptionField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(productDescriptionField, gbc);

        JLabel productPriceLabel = new JLabel("Precio:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(productPriceLabel, gbc);

        productPriceField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(productPriceField, gbc);

        addButton = new JButton("Agregar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        add(panel);

        addButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String productDescription = productDescriptionField.getText();
            double productPrice;
            try {
                productPrice = Double.parseDouble(productPriceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor complete los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!productName.isEmpty() && !productDescription.isEmpty()) {
                Producto nuevoProducto = new Producto(productName, productDescription, productPrice);
                try {
                    ordersFrame.agregarProducto(nuevoProducto);
                    JOptionPane.showMessageDialog(this, "Producto agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al agregar producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
