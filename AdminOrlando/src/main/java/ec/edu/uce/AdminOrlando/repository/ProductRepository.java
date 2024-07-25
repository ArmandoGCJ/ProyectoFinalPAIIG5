package ec.edu.uce.AdminOrlando.repository;

import ec.edu.uce.AdminOrlando.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
