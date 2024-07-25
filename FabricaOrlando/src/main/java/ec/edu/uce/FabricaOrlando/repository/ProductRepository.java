package ec.edu.uce.FabricaOrlando.repository;

import ec.edu.uce.FabricaOrlando.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
