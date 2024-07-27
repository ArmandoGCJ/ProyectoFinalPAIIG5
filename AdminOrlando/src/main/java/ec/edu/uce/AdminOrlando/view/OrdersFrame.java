package ec.edu.uce.AdminOrlando.view;

import javax.swing.*;
import java.awt.*;

public class OrdersFrame extends JFrame {
    private DefaultListModel<String> productListModel;
    private JList<String> productList;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    public OrdersFrame() {
        setTitle("Product List");
        setResizable(false);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(216, 20, 47));

        productListModel = new DefaultListModel<>();
        productListModel.addElement("Madera");
        productListModel.addElement("Metal");
        productListModel.addElement("Diamante");

        productList = new JList<>(productListModel);
        productList.setBackground(new Color(216, 20, 47));
        productList.setForeground(Color.WHITE);
        scrollPane = new JScrollPane(productList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topButtonPanel.setBackground(new Color(216, 20, 47));

        JButton productsButton = new JButton("Productos");
        JButton ordersButton = new JButton("Pedidos");

        topButtonPanel.add(productsButton);
        topButtonPanel.add(ordersButton);

        panel.add(topButtonPanel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Inicializar con botones de productos
        showProductButtons();

        // Acciones de botones superiores
        productsButton.addActionListener(e -> showProductButtons());
        ordersButton.addActionListener(e -> showOrderButtons());
    }

    private void showProductButtons() {
        buttonPanel.removeAll();
        scrollPane.setVisible(true);

        JButton addButton = new JButton("Agregar Producto");
        JButton removeButton = new JButton("Eliminar Producto");
        addButton.setPreferredSize(new Dimension(150, 35));
        removeButton.setPreferredSize(new Dimension(150, 35));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Acciones de botones
        addButton.addActionListener(e -> new AddOrders(this).setVisible(true));
        removeButton.addActionListener(e -> {
            int selectedIndex = productList.getSelectedIndex();
            if (selectedIndex != -1) {
                productListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un producto para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void showOrderButtons() {
        buttonPanel.removeAll();
        scrollPane.setVisible(false);

        JButton processButton = new JButton("Procesar Pedido");
        JButton deleteButton = new JButton("Eliminar Pedido");
        processButton.setPreferredSize(new Dimension(160, 35));
        deleteButton.setPreferredSize(new Dimension(150, 35));
        buttonPanel.add(processButton);
        buttonPanel.add(deleteButton);

        // Acciones de botones
        processButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Pedido procesado", "Información", JOptionPane.INFORMATION_MESSAGE));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Pedido eliminado", "Información", JOptionPane.INFORMATION_MESSAGE));

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public void addProduct(String productName) {
        productListModel.addElement(productName);
    }
}
