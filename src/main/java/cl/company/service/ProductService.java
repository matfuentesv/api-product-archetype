package cl.company.service;



import cl.company.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();
    Product findProduct(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    boolean existsProductByName(String username);
    boolean existsProductById(Long id);
}
