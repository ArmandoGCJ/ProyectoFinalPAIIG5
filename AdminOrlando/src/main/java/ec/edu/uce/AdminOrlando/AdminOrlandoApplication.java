package ec.edu.uce.AdminOrlando;

import ec.edu.uce.AdminOrlando.controller.AdminAppService;
import ec.edu.uce.AdminOrlando.service.ClientService;
import ec.edu.uce.AdminOrlando.model.Admin;
import ec.edu.uce.AdminOrlando.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class AdminOrlandoApplication implements CommandLineRunner {

	private final AdminAppService adminAppService;

	@Autowired
	public AdminOrlandoApplication(AdminAppService adminAppService) {
		this.adminAppService = adminAppService;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ec.edu.uce.AdminOrlando.view.LoginFrame().setVisible(true));
		SpringApplication.run(AdminOrlandoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		adminAppService.run();
		adminAppService.validateLogin("admin");
	}

}
