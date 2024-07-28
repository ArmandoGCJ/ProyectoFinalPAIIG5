package ec.edu.uce.AdminOrlando.view;

import ec.edu.uce.AdminOrlando.Api.ProductoApi;
import ec.edu.uce.AdminOrlando.Api.PedidoApi;
import ec.edu.uce.AdminOrlando.model.Producto;
import ec.edu.uce.AdminOrlando.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class OrdersFrame extends JFrame {
    private JScrollPane scrollPane;
    private ProductoApi productoApi = new ProductoApi();
    private PedidoApi pedidoApi = new PedidoApi();
    private JTable productTable;
    private DefaultTableModel productTableModel;
    private JTable orderTable;
    private DefaultTableModel orderTableModel;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton processButton;
    private JTextField deleteNameField;

    public OrdersFrame(JPanel jPanel) {
        setTitle("Product and Order List");
        setResizable(false);
        setSize(600, 700); // Aumentar el tamaño para mostrar más contenido
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        JPanel topButtonPanel = new JPanel();
        JButton productButton = new JButton("Productos");
        JButton orderButton = new JButton("Pedidos");
        topButtonPanel.add(productButton);
        topButtonPanel.add(orderButton);
        mainPanel.add(topButtonPanel);


        String[] productColumnNames = {"ID", "Nombre", "Descripción", "Precio"};
        productTableModel = new DefaultTableModel(productColumnNames, 0);
        productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        mainPanel.add(new JLabel("Productos:"));
        mainPanel.add(productScrollPane);


        String[] orderColumnNames = {"ID", "Cliente", "Estado"};
        orderTableModel = new DefaultTableModel(orderColumnNames, 0);
        orderTable = new JTable(orderTableModel);
        JScrollPane orderScrollPane = new JScrollPane(orderTable);
        mainPanel.add(new JLabel("Pedidos:"));
        mainPanel.add(orderScrollPane);


        controlPanel = new JPanel();
        deleteNameField = new JTextField(10);
        addButton = new JButton("Agregar Producto");
        deleteButton = new JButton("Eliminar Producto");
        processButton = new JButton("Procesar Pedido");
        controlPanel.add(addButton);
        controlPanel.add(new JLabel("Nombre a eliminar:"));
        controlPanel.add(deleteNameField);
        controlPanel.add(deleteButton);
        controlPanel.add(processButton);
        mainPanel.add(controlPanel);

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        showProducts();

        productButton.addActionListener(e -> showProducts());
        orderButton.addActionListener(e -> showOrders());

        addButton.addActionListener(e -> new AddOrders(this).setVisible(true));
        deleteButton.addActionListener(e -> deleteProduct());
        processButton.addActionListener(e -> processOrder());

        loadProducts();
        loadOrders();
    }

    private void showProducts() {
        productTable.setVisible(true);
        orderTable.setVisible(false);
        addButton.setVisible(true);
        deleteButton.setText("Eliminar Producto");
        processButton.setVisible(false);
    }

    private void showOrders() {
        productTable.setVisible(false);
        orderTable.setVisible(true);
        addButton.setVisible(false);
        deleteButton.setText("Eliminar Pedido");
        processButton.setVisible(true);
    }

    private void loadProducts() {
        try {
            List<Producto> productos = productoApi.obtenerTodosLosProductos();
            for (Producto producto : productos) {
                Object[] row = {producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio()};
                productTableModel.addRow(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Error al obtener productos");
            mainPanel.add(errorLabel);
        }
    }

    private void loadOrders() {
        try {
            List<Pedido> pedidos = pedidoApi.obtenerTodosLosPedidos();
            for (Pedido pedido : pedidos) {
                Object[] row = {pedido.getId(), pedido.getCliente().getNombre(), pedido.getEstado()};
                orderTableModel.addRow(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Error al obtener pedidos");
            mainPanel.add(errorLabel);
        }
    }

    public void addProduct(Producto nuevoProducto) {
        try {

            productoApi.agregarProducto(nuevoProducto);

            Producto productoConId = productoApi.obtenerProductoPorNombre(nuevoProducto.getNombre());

            Object[] row = {productoConId.getId(), productoConId.getNombre(), productoConId.getDescripcion(), productoConId.getPrecio()};
            productTableModel.addRow(row);
            productTable.revalidate();
            productTable.repaint();

            JOptionPane.showMessageDialog(this, "Producto agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        String nombre = deleteNameField.getText();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del producto a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int rowCount = productTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (productTableModel.getValueAt(i, 1).equals(nombre)) {
                Long productId = (Long) productTableModel.getValueAt(i, 0);
                try {
                    productoApi.eliminarProducto(productId);
                    productTableModel.removeRow(i);
                    productTable.revalidate();
                    productTable.repaint();

                    JOptionPane.showMessageDialog(this, "Producto eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al eliminar producto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void processOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow != -1) {
            Long orderId = (Long) orderTableModel.getValueAt(selectedRow, 0);
            try {
                pedidoApi.procesarPedido(orderId);
                orderTableModel.setValueAt("Procesado", selectedRow, 2);
                orderTable.revalidate();
                orderTable.repaint();


                JOptionPane.showMessageDialog(this, "Pedido procesado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al procesar pedido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para procesar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
