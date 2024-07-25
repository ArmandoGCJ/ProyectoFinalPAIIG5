package ec.edu.uce.FabricaOrlando.repository;

import ec.edu.uce.FabricaOrlando.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase,Long> {
}
