package ec.edu.uce.Persistencia.Orlando.repository;

import ec.edu.uce.Persistencia.Orlando.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
