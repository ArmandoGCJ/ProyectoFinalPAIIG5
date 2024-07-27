package ec.edu.uce.Persistencia.Orlando.repository;

import ec.edu.uce.Persistencia.Orlando.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
