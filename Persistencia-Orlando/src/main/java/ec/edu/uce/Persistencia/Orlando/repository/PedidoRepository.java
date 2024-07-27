package ec.edu.uce.Persistencia.Orlando.repository;

import ec.edu.uce.Persistencia.Orlando.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
