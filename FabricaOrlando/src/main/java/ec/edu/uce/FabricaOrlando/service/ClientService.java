package ec.edu.uce.FabricaOrlando.service;

import ec.edu.uce.FabricaOrlando.model.Client;
import ec.edu.uce.FabricaOrlando.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Optional<Client> findByUserName(String userName) {
        return clientRepository.findByUserName(userName);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
