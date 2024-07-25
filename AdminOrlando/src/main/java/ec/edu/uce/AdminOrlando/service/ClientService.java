package ec.edu.uce.AdminOrlando.service;

import ec.edu.uce.AdminOrlando.model.Admin;
import ec.edu.uce.AdminOrlando.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Admin> findByUserName(String userName) {
        return clientRepository.findByUserName(userName);
    }

    public Admin save(Admin admin) {
        return clientRepository.save(admin);
    }
}
