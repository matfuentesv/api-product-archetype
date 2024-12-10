package com.compony;

import cl.company.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testBuilder() {
        Product product = new Product.Builder()
                .id(1L)
                .name("Producto 1")
                .price(100.0)
                .description("Descripción del producto 1")
                .discount(10.0)
                .image("imagen.jpg")
                .category("Categoría 1")
                .originalPrice(120.0)
                .rating(5)
                .reviews(10)
                .quantity(50)
                .build();

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Producto 1", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals("Descripción del producto 1", product.getDescription());
        assertEquals(10.0, product.getDiscount());
        assertEquals("imagen.jpg", product.getImage());
        assertEquals("Categoría 1", product.getCategory());
        assertEquals(120.0, product.getOriginalPrice());
        assertEquals(5, product.getRating());
        assertEquals(10, product.getReviews());
        assertEquals(50, product.getQuantity());
    }

    @Test
    void testSettersAndGetters() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Producto 1");
        product.setPrice(100.0);
        product.setDescription("Descripción del producto 1");
        product.setDiscount(10.0);
        product.setImage("imagen.jpg");
        product.setCategory("Categoría 1");
        product.setOriginalPrice(120.0);
        product.setRating(5);
        product.setReviews(10);
        product.setQuantity(50);

        assertEquals(1L, product.getId());
        assertEquals("Producto 1", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals("Descripción del producto 1", product.getDescription());
        assertEquals(10.0, product.getDiscount());
        assertEquals("imagen.jpg", product.getImage());
        assertEquals("Categoría 1", product.getCategory());
        assertEquals(120.0, product.getOriginalPrice());
        assertEquals(5, product.getRating());
        assertEquals(10, product.getReviews());
        assertEquals(50, product.getQuantity());
    }

    @Test
    void testEqualsAndHashCode() {
        Product product1 = new Product.Builder()
                .id(1L)
                .name("Producto 1")
                .price(100.0)
                .description("Descripción del producto 1")
                .build();

        Product product2 = new Product.Builder()
                .id(1L)
                .name("Producto 1")
                .price(100.0)
                .description("Descripción del producto 1")
                .build();

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }


}