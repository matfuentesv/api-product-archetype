package cl.company.service.impl;


import cl.company.exception.ApiResponse;
import cl.company.model.Product;
import cl.company.model.ProductResponse;
import cl.company.repository.ProductRepository;
import cl.company.service.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Log
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse findAllProduct() {
        final Predicate<Product>notebookPredicate = x->x.getCategory().equalsIgnoreCase("notebooks");
        final Predicate<Product>cellPhonesPredicate = x->x.getCategory().equalsIgnoreCase("cellPhones");
        final Predicate<Product>airConditioningPredicate = x->x.getCategory().equalsIgnoreCase("airConditioning");
        final Predicate<Product>coffeePredicate = x->x.getCategory().equalsIgnoreCase("coffeeMakers");
        final Predicate<Product>outstandingPredicate = x->x.getCategory().equalsIgnoreCase("outstanding");

        final List<Product>productList = productRepository.findAll();
        final List<Product>notebookList = productList.stream().filter(notebookPredicate).collect(Collectors.toList());
        final List<Product>cellPhonesList = productList.stream().filter(cellPhonesPredicate).collect(Collectors.toList());
        final List<Product>airConditioningList = productList.stream().filter(airConditioningPredicate).collect(Collectors.toList());
        final List<Product>coffeeList = productList.stream().filter(coffeePredicate).collect(Collectors.toList());

        final List<Product>outstandingList = productList.stream().filter(outstandingPredicate).collect(Collectors.toList());

       return new ProductResponse()
               .setNotebooks(notebookList)
               .setCellPhones(cellPhonesList)
               .setAirConditioning(airConditioningList)
               .setCoffeeMakers(coffeeList)
               .setOutstanding(outstandingList);
    }

    @Override
    public Product findProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElseGet(() -> new Product.Builder().build());
    }

    @Override
    public Product createProduct(Product product) {

        if(!existsProductByName(product.getName())){
            Product productToCreate = new Product.Builder()
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .description(product.getDescription())
                                        .discount(product.getDiscount())
                                        .image(product.getImage())
                                        .category(product.getCategory())
                                        .originalPrice(product.getOriginalPrice())
                                        .rating(product.getRating())
                                        .reviews(product.getReviews())
                                        .quantity(product.getQuantity())
                                        .build();
            return productRepository.save(productToCreate);

        }else {
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        if(existsProductById(product.getId())){
            Product productToUpdate = new Product.Builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .description(product.getDescription())
                                        .discount(product.getDiscount())
                                        .image(product.getImage())
                                        .category(product.getCategory())
                                        .originalPrice(product.getOriginalPrice())
                                        .rating(product.getRating())
                                        .reviews(product.getReviews())
                                        .quantity(product.getQuantity())
                                        .build();
            return productRepository.save(productToUpdate);
        }else {
            log.info("No se puedo actualizar el product,no existe");
            return null;
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
