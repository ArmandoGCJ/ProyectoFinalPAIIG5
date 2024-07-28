package ec.edu.uce.FabricaOrlando;

import ec.edu.uce.FabricaOrlando.view.AuthFrame;

import javax.swing.*;

public class FabricaOrlandoApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new AuthFrame().setVisible(true));

	}
}
