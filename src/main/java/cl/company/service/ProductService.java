package cl.company.service;



import cl.company.model.Product;
import cl.company.model.ProductResponse;
import org.springframework.http.ResponseEntity;


public interface ProductService {

    ProductResponse findAllProduct();
    Product findProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    ResponseEntity<Object> deleteProduct(Long id);
    boolean existsProductByName(String username);
    boolean existsProductById(Long id);
}
