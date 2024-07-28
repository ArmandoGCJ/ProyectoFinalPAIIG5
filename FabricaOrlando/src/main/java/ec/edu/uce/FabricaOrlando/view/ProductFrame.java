package ec.edu.uce.FabricaOrlando.view;

import ec.edu.uce.FabricaOrlando.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFrame extends JFrame{
    List<Product> productos = new ArrayList<>();

    public ProductFrame() {

        productos.add(new Product("src/main/resources/spremium.png", "Product 1<br>Material: Madera Guayacán blanco<br>Medidas: 33.5 x 30 cm<br>Altura: 62 cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/diesel.png", "Product 2<br>Material: Madera Guayacán blanco<br>Medidas: 120 x 60 cm<br>Altura: 50 cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/extra.png", "Product 3<br>Material: metal<br>Medidas: 96 x 205 cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/hander.png", "Product 4<br>Material: Madera<br>Medidas: 110x39xh96cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/spremium.png", "Product 1<br>Material: Madera Guayacán blanco<br>Medidas: 33.5 x 30 cm<br>Altura: 62 cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/diesel.png", "Product 2<br>Material: Madera Guayacán blanco<br>Medidas: 120 x 60 cm<br>Altura: 50 cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/extra.png", "Product 3<br>Material: metal<br>Medidas: 96 x 205 cm<br>Piezas: 1 pieza."));
        productos.add(new Product("src/main/resources/hander.png", "Product 4<br>Material: Madera<br>Medidas: 110x39xh96cm<br>Piezas: 1 pieza."));
        // Puedes agregar más productos a la lista

        // Crear el marco principal
        setTitle("Interfaz de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Crear el panel principal con un GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Añadir los productos al panel
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

        // Crear un JScrollPane y añadir el panel principal a él
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Añadir el JScrollPane al marco y hacerlo visible
        add(scrollPane);

    }

    private static JPanel crearPanelProducto(Product producto) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel imagen = new JLabel(new ImageIcon(producto.getImagenPath()));
        JLabel descripcion = new JLabel("<html>" + producto.getDescription() + "</html>");
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton boton = new JButton("Detalles");
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(imagen);
        panel.add(descripcion);
        panel.add(boton);

        return panel;
    }


}
