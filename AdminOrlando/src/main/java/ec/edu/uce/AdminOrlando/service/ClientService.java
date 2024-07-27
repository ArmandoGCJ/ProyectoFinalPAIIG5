package ec.edu.uce.AdminOrlando.service;

import ec.edu.uce.AdminOrlando.model.Admin;
import ec.edu.uce.AdminOrlando.repository.ClientRepository;

import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Admin> findByUserName(String username) {
        return clientRepository.findByUserName(username);
    }

    public void save(Admin admin) {
        clientRepository.save(admin);
    }
}
