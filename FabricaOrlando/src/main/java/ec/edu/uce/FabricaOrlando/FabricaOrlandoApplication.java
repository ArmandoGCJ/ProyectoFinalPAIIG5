package ec.edu.uce.FabricaOrlando;

import ec.edu.uce.FabricaOrlando.model.Client;
import ec.edu.uce.FabricaOrlando.service.ClientService;
import ec.edu.uce.FabricaOrlando.useful.Rol;
import ec.edu.uce.FabricaOrlando.view.LoginFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class FabricaOrlandoApplication implements CommandLineRunner {
	@Autowired
	private ClientService clientService;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
		SpringApplication.run(FabricaOrlandoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String name = "Hola";
		String password = "amigo";

		Client client = new Client();
		client.setUserName(name);
		client.setPassword(password);
		client.setRol(Rol.ADMIN);
		clientService.save(client);

		System.out.println("Client saved: " + client.getUserName());
	}
}
