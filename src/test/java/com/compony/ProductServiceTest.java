package com.compony;

import cl.company.model.Product;
import cl.company.model.ProductResponse;
import cl.company.repository.ProductRepository;
import cl.company.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllProduct_ReturnsProductResponse() {
        Product product = new Product.Builder()
                .name("Laptop")
                .category("notebooks")
                .build();

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        ProductResponse response = productService.findAllProduct();

        assertNotNull(response);
        assertEquals(1, response.getNotebooks().size());
        assertEquals("Laptop", response.getNotebooks().get(0).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findProduct_ProductExists_ReturnsProduct() {
        Long productId = 1L;
        Product product = new Product.Builder()
                .id(productId)
                .name("Laptop")
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findProduct(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
        assertEquals("Laptop", foundProduct.getName());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void findProduct_ProductDoesNotExist_ReturnsEmptyProduct() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product foundProduct = productService.findProduct(productId);

        assertNotNull(foundProduct);
        assertNull(foundProduct.getId());
        assertNull(foundProduct.getName());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void createProduct_NewProduct_ReturnsCreatedProduct() {
        Product product = new Product.Builder()
                .name("Phone")
                .build();

        when(productRepository.findProductByName(product.getName())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Phone", createdProduct.getName());

        verify(productRepository, times(1)).findProductByName(product.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_ProductAlreadyExists_ReturnsNull() {
        Product product = new Product.Builder()
                .name("Phone")
                .build();

        when(productRepository.findProductByName(product.getName())).thenReturn(Optional.of(product));

        Product createdProduct = productService.createProduct(product);

        assertNull(createdProduct);

        verify(productRepository, times(1)).findProductByName(product.getName());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void updateProduct_ProductExists_ReturnsUpdatedProduct() {
        Product product = new Product.Builder()
                .id(1L)
                .name("Updated Phone")
                .build();

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);

        assertNotNull(updatedProduct);
        assertEquals("Updated Phone", updatedProduct.getName());

        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_ProductDoesNotExist_ReturnsNull() {
        Product product = new Product.Builder()
                .id(1L)
                .build();

        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        Product updatedProduct = productService.updateProduct(product);

        assertNull(updatedProduct);

        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void deleteProduct_ProductExists_ReturnsSuccessResponse() {
        Long productId = 1L;
        Product product = new Product.Builder().id(productId).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(productId);

        ResponseEntity<Object> response = productService.deleteProduct(productId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Eliminación exitosa", response.getBody());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void deleteProduct_ProductDoesNotExist_ReturnsBadRequest() {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = productService.deleteProduct(productId);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).deleteById(anyLong());
    }
}
