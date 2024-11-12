package cl.company.service;



import cl.company.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();
    Product findProduct(Long id);
    ResponseEntity<Object> createProduct(Product product);
    ResponseEntity<Object> updateProduct(Product product);
    ResponseEntity<Object> deleteProduct(Long id);
    boolean existsProductByName(String username);
    boolean existsProductById(Long id);
}
