package ec.edu.uce.AdminOrlando.controller;

import ec.edu.uce.AdminOrlando.model.Admin;
import ec.edu.uce.AdminOrlando.service.ClientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAppService {

    private final ClientService clientService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AdminAppService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Transactional
    public void run() {
        Admin admin = new Admin();
        admin.setUserName("admin");
        admin.setPassword("admin");
        clientService.save(admin);

        System.out.println("Client saved: " + admin.getUserName());

        // Guardamos las entidades y sus debidas relaciones
        clientService.save(admin);

        // Imprimimos los usuarios, perfiles,posts y tags
    }

    @Transactional
    public void validateLogin(String username){
        boolean isValid = clientService.findByUserName(username).isEmpty();
        System.out.println(isValid);
    }

}