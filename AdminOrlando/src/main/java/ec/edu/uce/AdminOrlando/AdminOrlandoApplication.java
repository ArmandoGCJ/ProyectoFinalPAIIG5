package ec.edu.uce.AdminOrlando;

import javax.swing.*;

/**
 *
 * @author Cristian Lechon , Edgar Tipan  y  Armando Valle.
 * Titulo: Proyecto Grupal Final Grupo 5.
 * Fabrica Orlando.
 */

public class AdminOrlandoApplication {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ec.edu.uce.AdminOrlando.view.LoginFrame().setVisible(true));

    }

}


