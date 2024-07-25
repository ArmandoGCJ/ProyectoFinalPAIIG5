package ec.edu.uce.AdminOrlando.repository;

import ec.edu.uce.AdminOrlando.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Purchase,Long> {
}
