package ec.edu.uce.Persistencia.Orlando;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PersistenciaOrlandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenciaOrlandoApplication.class, args);
	}

}
