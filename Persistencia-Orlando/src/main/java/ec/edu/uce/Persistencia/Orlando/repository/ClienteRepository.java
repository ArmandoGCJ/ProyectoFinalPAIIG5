package ec.edu.uce.Persistencia.Orlando.repository;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNombreAndContrasena(String nombre, String contrasena);
}
