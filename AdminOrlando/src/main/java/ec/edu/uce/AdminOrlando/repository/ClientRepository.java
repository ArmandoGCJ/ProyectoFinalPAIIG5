package ec.edu.uce.AdminOrlando.repository;

import ec.edu.uce.AdminOrlando.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUserName(String userName);
}
