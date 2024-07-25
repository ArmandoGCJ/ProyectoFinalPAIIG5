package ec.edu.uce.FabricaOrlando.service;

import ec.edu.uce.FabricaOrlando.model.Product;
import ec.edu.uce.FabricaOrlando.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

}
