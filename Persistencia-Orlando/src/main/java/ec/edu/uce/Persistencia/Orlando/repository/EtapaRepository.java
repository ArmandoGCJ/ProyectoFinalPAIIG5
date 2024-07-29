package ec.edu.uce.Persistencia.Orlando.repository;

import ec.edu.uce.Persistencia.Orlando.model.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapaRepository extends JpaRepository<Etapa, Long> {
    List<Etapa> findByProductoId(Long productoId);
}
