package ec.edu.uce.FabricaOrlando.view;

import ec.edu.uce.FabricaOrlando.Controller.Container;
import ec.edu.uce.FabricaOrlando.model.Product;
import ec.edu.uce.FabricaOrlando.model.Etapa;
import ec.edu.uce.FabricaOrlando.model.ObserverClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductFrame extends JFrame {
    private List<Product> productos;
    private List<Product> carrito;
    private final Container container;
    private ExecutorService executor;

    public ProductFrame() {
        container = new Container();
        productos = container.getAllProducts();
        if (productos == null) {
            productos = new ArrayList<>();
        }

        carrito = new ArrayList<>();
        executor = Executors.newFixedThreadPool(2);

        setTitle("Interfaz de Productos");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        int row = 0;
        int col = 0;
        for (Product producto : productos) {
            gbc.gridx = col;
            gbc.gridy = row;
            mainPanel.add(crearPanelProducto(producto), gbc);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel topPanel = new JPanel(new BorderLayout());

        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.addActionListener(e -> {
            new AuthFrame().setVisible(true);
            dispose();
        });
        topPanel.add(logoutButton, BorderLayout.EAST);

        JButton carritoButton = new JButton("Carrito de Compras");
        carritoButton.addActionListener(e -> mostrarCarritoCompras());
        topPanel.add(carritoButton, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelProducto(Product producto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel imagen = new JLabel(new ImageIcon(producto.getImagenPath()));
        JLabel name = new JLabel("<html>" + producto.getNombre() + "</html>");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton detallesButton = new JButton("Detalles");
        detallesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statusLabel = new JLabel("Estado: Disponible");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        detallesButton.addActionListener(e -> mostrarDetallesProducto(producto));

        JButton agregarCarritoButton = new JButton("Agregar al Carrito");
        agregarCarritoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        agregarCarritoButton.addActionListener(e -> {
            carrito.add(producto);
        });

        panel.add(imagen);
        panel.add(name);
        panel.add(detallesButton);
        panel.add(agregarCarritoButton);
        panel.add(statusLabel);

        return panel;
    }

    private void mostrarDetallesProducto(Product producto) {
        JOptionPane.showMessageDialog(this,
                "<html>Descripción: " + producto.getDescripcion() + "<br/>Precio: $" + producto.getPrecio() + "</html>",
                "Descripción del Producto",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarCarritoCompras() {
        JDialog carritoDialog = new JDialog(this, "Carrito de Compras", true);
        carritoDialog.setSize(800, 600);
        carritoDialog.setLayout(new BorderLayout());

        JPanel carritoPanel = new JPanel();
        carritoPanel.setLayout(new BoxLayout(carritoPanel, BoxLayout.Y_AXIS));

        for (Product producto : carrito) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(new BoxLayout(panelProducto, BoxLayout.Y_AXIS));
            panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel nombreLabel = new JLabel("<html>Producto: " + producto.getNombre() + "<br/>Precio: $" + producto.getPrecio() + "</html>");
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);
            JLabel statusLabel = new JLabel("Estado: Pendiente");

            panelProducto.add(nombreLabel);
            panelProducto.add(progressBar);
            panelProducto.add(statusLabel);

            iniciarProcesamientoProducto(producto, progressBar, statusLabel);

            carritoPanel.add(panelProducto);
        }

        JScrollPane scrollPane = new JScrollPane(carritoPanel);
        carritoDialog.add(scrollPane, BorderLayout.CENTER);

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(e -> carritoDialog.dispose());
        carritoDialog.add(cerrarButton, BorderLayout.SOUTH);

        carritoDialog.setVisible(true);
    }

    private void iniciarProcesamientoProducto(Product producto, JProgressBar progressBar, JLabel statusLabel) {
        List<Etapa> etapas = container.getEtapasPorProductoId(producto.getId());
        ObserverClass observer = new ObserverClass(progressBar, statusLabel);

        for (Etapa etapa : etapas) {
            etapa.agregarObserver(observer);
        }

        executor.execute(() -> {
            for (Etapa etapa : etapas) {
                try {
                    etapa.ejecutarEtapa();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
