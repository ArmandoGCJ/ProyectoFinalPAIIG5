package ec.edu.uce.AdminOrlando.repository;

import ec.edu.uce.AdminOrlando.model.Admin;
import java.util.Optional;

public interface ClientRepository {
    Optional<Admin> findByUserName(String username);
    void save(Admin admin);
}
