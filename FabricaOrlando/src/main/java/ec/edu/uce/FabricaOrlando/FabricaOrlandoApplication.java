package ec.edu.uce.FabricaOrlando;

import ec.edu.uce.FabricaOrlando.view.LoginFrame;
import javax.swing.*;

public class FabricaOrlandoApplication {


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));

	}

}
