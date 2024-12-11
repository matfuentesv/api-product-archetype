
package com.compony;

import cl.company.controller.ProductController;
import cl.company.exception.ApiResponse;
import cl.company.model.Product;
import cl.company.model.ProductResponse;
import cl.company.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllProduct_Successful() {
        ProductResponse mockProductResponse = new ProductResponse()
                .setNotebooks(Collections.singletonList(new Product.Builder()
                        .name("Laptop")
                        .price(1000.0)
                        .description("A powerful laptop")
                        .build()))
                .setOutstanding(Collections.singletonList(new Product.Builder()
                        .name("Smartphone")
                        .price(800.0)
                        .description("A high-end smartphone")
                        .build()));

        when(productService.findAllProduct()).thenReturn(mockProductResponse);
        ResponseEntity<Object> result = productController.findAllProduct();
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(mockProductResponse, result.getBody());
        verify(productService, times(1)).findAllProduct();
    }


    @Test
    void findProduct_Successful() {

        Long id = 1L;
        Product mockProduct = new Product.Builder()
                .id(id)
                .name("Laptop")
                .price(1000.0)
                .description("A powerful laptop")
                .build();
        when(productService.findProduct(id)).thenReturn(mockProduct);


        ResponseEntity<Object> result = productController.findProduct(id);
        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(mockProduct, result.getBody());
        verify(productService, times(1)).findProduct(id);
    }

    @Test
    void findProduct_InvalidId() {

        Long id = null;


        ResponseEntity<Object> result = productController.findProduct(id);


        assertNotNull(result);
        assertEquals(400, result.getStatusCodeValue());
        ApiResponse apiResponse = (ApiResponse) result.getBody();
        assertEquals("Algunos de los parámetros no se ingresaron", apiResponse.getMessage());
    }

    @Test
    void createProduct_Successful() {
        // Arrange
        Product product = new Product.Builder()
                .name("Phone")
                .price(500.0)
                .description("A smartphone")
                .build();
        when(productService.createProduct(product)).thenReturn(product);


        Product result = productController.createProduct(product);
        assertNotNull(result);
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        verify(productService, times(1)).createProduct(product);
    }



    @Test
    void updateProduct_Successful() {

        Product existingProduct = new Product.Builder()
                .id(1L)
                .name("Old Laptop")
                .price(900.0)
                .description("An old laptop description")
                .discount(10.0)
                .image("old-image.jpg")
                .category("Electronics")
                .quantity(10)
                .rating(4)
                .reviews(20)
                .build();


        Product updatedProduct = new Product.Builder()
                .id(1L)
                .name("Updated Laptop")
                .price(1200.0)
                .description("An updated laptop description")
                .discount(15.0)
                .image("updated-image.jpg")
                .category("Electronics")
                .quantity(15)
                .rating(5)
                .reviews(50)
                .build();


        when(productService.updateProduct(existingProduct)).thenReturn(updatedProduct);


        Product result = productController.updateProduct(existingProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getId(), result.getId());
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getDescription(), result.getDescription());
        assertEquals(updatedProduct.getPrice(), result.getPrice());

        verify(productService, times(1)).updateProduct(existingProduct);
    }


    @Test
    void deleteProduct_Successful() {
        Long productId = 1L;
        Product mockProduct = new Product.Builder()
                .id(productId)
                .name("Laptop")
                .price(1000.0)
                .description("A high-end laptop")
                .discount(10.0)
                .image("laptop.jpg")
                .category("Electronics")
                .quantity(5)
                .rating(5)
                .reviews(10)
                .build();

        ApiResponse expectedResponse = new ApiResponse("Usuario eliminado", true);


        when(productService.findProduct(productId)).thenReturn(mockProduct);
        doReturn(ResponseEntity.ok(expectedResponse)).when(productService).deleteProduct(productId);


        ResponseEntity<Object> result = productController.deleteProduct(productId);


        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());


        ApiResponse actualResponse = (ApiResponse) result.getBody();
        assertNotNull(actualResponse);



        verify(productService, times(1)).findProduct(productId);
        verify(productService, times(1)).deleteProduct(productId);
    }





    @Test
    void deleteProduct_NotFound() {
        Long productId = 1L;

        when(productService.findProduct(productId)).thenReturn(null);

        ResponseEntity<Object> result = productController.deleteProduct(productId);

        assertNotNull(result);
        assertEquals(404, result.getStatusCodeValue());
        assertFalse(((ApiResponse) result.getBody()).isSuccess());
        verify(productService, times(1)).findProduct(productId);
        verify(productService, never()).deleteProduct(productId);
    }





}