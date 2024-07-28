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
        setSize(300, 300);
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

        JLabel productDescriptionLabel = new JLabel("Descripción:");
        productDescriptionLabel.setBounds(10, 60, 150, 25);
        panel.add(productDescriptionLabel);

        productDescriptionField = new JTextField(20);
        productDescriptionField.setBounds(170, 60, 100, 25);
        panel.add(productDescriptionField);

        JLabel productPriceLabel = new JLabel("Precio:");
        productPriceLabel.setBounds(10, 100, 150, 25);
        panel.add(productPriceLabel);

        productPriceField = new JTextField(20);
        productPriceField.setBounds(170, 100, 100, 25);
        panel.add(productPriceField);

        addButton = new JButton("Agregar");
        addButton.setBounds(10, 140, 260, 35);
        addButton.setBackground(new Color(246, 246, 246));
        panel.add(addButton);

        add(panel);
        addButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String productDescription = productDescriptionField.getText();
            double productPrice;
            try {
                productPrice = Double.parseDouble(productPriceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un precio válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!productName.isEmpty() && !productDescription.isEmpty()) {
                Producto nuevoProducto = new Producto(productName, productDescription, productPrice);
                try {
                    ordersFrame.addProduct(nuevoProducto);
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
