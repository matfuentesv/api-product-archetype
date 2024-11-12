package cl.company.service.impl;


import cl.company.exception.ApiResponse;
import cl.company.model.Product;
import cl.company.repository.ProductRepository;
import cl.company.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseGet(() -> new Product.Builder().build());
    }

    @Override
    public ResponseEntity<Object> createProduct(Product product) {

        if(!existsProductByName(product.getName())){
            Product productToCreate = new Product.Builder()
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .description(product.getDescription())
                                        .build();
            Product createdProduct = productRepository.save(productToCreate);
            if(createdProduct == null){
                log.info("Algunos de los parámetros no se ingresaron");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Algunos de los parámetros no se ingresaron",false));
            }else {
                return ResponseEntity.ok(product);
            }
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("No se puedo actualizar el product,no existe",false));
        }
    }

    @Override
    public ResponseEntity<Object> updateProduct(Product product) {
        if(!existsProductById(product.getId())){
            Product productToUpdate = new Product.Builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .description(product.getDescription())
                                        .build();
            return ResponseEntity.ok(productRepository.save(productToUpdate));
        }else {
            log.info("No se puedo actualizar el product,no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("No se puedo actualizar el product,no existe",false));
        }
    }

    @Override
    public ResponseEntity<Object> deleteProduct(Long id) {

        if (!existsProductById(id)) {
            log.info("No se puede eliminar el product, no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("El product no existe",false));
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("Eliminación exitosa");
    }

    @Override
    public boolean existsProductByName(String name) {
        return productRepository.findProductByName(name).isPresent();
    }

    @Override
    public boolean existsProductById(Long id) {
        return productRepository.findById(id).isPresent();
    }
}
