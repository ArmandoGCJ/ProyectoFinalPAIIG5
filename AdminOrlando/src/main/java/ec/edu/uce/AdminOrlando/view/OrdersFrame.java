package ec.edu.uce.AdminOrlando.view;

import ec.edu.uce.AdminOrlando.Api.ProductoApi;
import ec.edu.uce.AdminOrlando.Api.PedidoApi;
import ec.edu.uce.AdminOrlando.model.Producto;
import ec.edu.uce.AdminOrlando.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class OrdersFrame extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductoApi productoApi = new ProductoApi();
    private PedidoApi pedidoApi = new PedidoApi();
    private boolean esVistaProducto = true;
    private JPanel panelInferior;
    private JPanel panelLateral;

    public OrdersFrame(JPanel jPanel) {
        setTitle("Gestión de productos y pedidos");
        setResizable(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);


        inicializarVista();
        inicializarBotones();
        mostrarProductos();

    }

    private void inicializarVista() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Precio"}, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

    }

    private void inicializarBotones() {
        JPanel panelBotones = new JPanel();
        JButton botonVerProductos = new JButton("Ver Productos");
        JButton botonVerPedidos = new JButton("Ver Pedidos");


        panelBotones.add(botonVerProductos);
        panelBotones.setBackground((new Color(24, 246, 246)));
        panelBotones.add(botonVerPedidos);
        mainPanel.add(panelBotones, BorderLayout.NORTH);


        botonVerProductos.addActionListener(e -> mostrarProductos());
        botonVerPedidos.addActionListener(e -> mostrarPedidos());
    }

    private void mostrarProductos() {
        if (!esVistaProducto) {
            cambiarVista("productos");
        }
        cargarProductos();
    }

    private void mostrarPedidos() {
        if (esVistaProducto) {
            cambiarVista("pedidos");
        }
        cargarPedidos();
    }

    private void cambiarVista(String tipoVista) {

        mainPanel.remove(scrollPane);
        if (panelInferior != null) {
            mainPanel.remove(panelInferior);
        }
        if (panelLateral != null) {
            mainPanel.remove(panelLateral);
        }

        if (tipoVista.equals("productos")) {
            configurarBotonesProducto();
            esVistaProducto = true;
        } else if (tipoVista.equals("pedidos")) {
            configurarBotonesPedido();
            esVistaProducto = false;
        }

        inicializarVista();

        SwingUtilities.invokeLater(() -> {
            mainPanel.revalidate();
            mainPanel.repaint();
        });
    }

    private void configurarBotonesProducto() {
        panelInferior = new JPanel();
        JButton botonAgregarProducto = new JButton("Agregar Producto");
        JButton botonEliminarProducto = new JButton("Eliminar Producto");

        panelInferior.add(botonAgregarProducto);
        panelInferior.add(botonEliminarProducto);
        panelInferior.setBackground((new Color(24, 246, 246)));
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        botonAgregarProducto.addActionListener(e -> new AddOrders(this).setVisible(true));
        botonEliminarProducto.addActionListener(e -> eliminarProducto());
    }

    private void configurarBotonesPedido() {
        panelLateral = new JPanel();
        JButton botonEtapas = new JButton("Etapas");
        JButton botonAumentarEtapas = new JButton("Aumentar Etapas");
        JButton botonEliminarEtapas = new JButton("Eliminar Etapas");
        panelLateral.add(botonEtapas);
        panelLateral.add(botonAumentarEtapas);
        panelLateral.add(botonEliminarEtapas);

        panelLateral.setBackground((new Color(255 * 65536 + 255 * 256 + 0)));
        mainPanel.add(panelLateral, BorderLayout.EAST);

        panelInferior = new JPanel();
        JButton botonProcesar = new JButton("Procesar");
        JButton botonCancelar = new JButton("Cancelar");
        panelInferior.add(botonProcesar);
        panelInferior.add(botonCancelar);
        panelInferior.setBackground((new Color(24, 246, 246)));
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        botonEtapas.addActionListener(e -> mostrarEtapas());
        botonAumentarEtapas.addActionListener(e -> aumentarEtapas());
        botonEliminarEtapas.addActionListener(e -> eliminarEtapas());
        botonProcesar.addActionListener(e -> procesarPedido());
        botonCancelar.addActionListener(e -> cancelarPedido());
    }

    private void cargarProductos() {
        new Thread(() -> {
            try {
                List<Producto> productos = productoApi.obtenerTodosLosProductos();
                SwingUtilities.invokeLater(() -> {
                    tableModel.setRowCount(0);
                    for (Producto producto : productos) {
                        tableModel.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio()});
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(this, "Error al obtener productos", "Error", JOptionPane.ERROR_MESSAGE)
                );
            }
        }).start();
    }

    private void cargarPedidos() {
        new Thread(() -> {
            try {
                List<Pedido> pedidos = pedidoApi.obtenerTodosLosPedidos();
                SwingUtilities.invokeLater(() -> {
                    tableModel.setRowCount(0);
                    for (Pedido pedido : pedidos) {
                        for (Producto producto : pedido.getProductos()) {
                            tableModel.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio()});
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(this, "Error al obtener pedidos", "Error", JOptionPane.ERROR_MESSAGE)
                );
            }
        }).start();
    }

    private void mostrarEtapas() {

    }

    private void aumentarEtapas() {

    }

    private void eliminarEtapas() {

    }

    private void procesarPedido() {

    }

    private void cancelarPedido() {

    }

    private void eliminarProducto() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            Long idProducto = (Long) tableModel.getValueAt(filaSeleccionada, 0);
            try {
                productoApi.eliminarProducto(idProducto);
                tableModel.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar producto", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void agregarProducto(Producto nuevoProducto) {
        try {
            productoApi.agregarProducto(nuevoProducto);
            cargarProductos();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
