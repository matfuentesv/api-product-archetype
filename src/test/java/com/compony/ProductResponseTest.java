package com.compony;

import cl.company.model.Product;
import cl.company.model.ProductResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResponseTest {

    @Test
    void testGetCellPhones() {
        // Arrange
        Product phone1 = new Product.Builder()
                .name("Phone 1")
                .price(700.0)
                .description("Smartphone 1")
                .build();

        Product phone2 = new Product.Builder()
                .name("Phone 2")
                .price(800.0)
                .description("Smartphone 2")
                .build();

        List<Product> cellPhones = Arrays.asList(phone1, phone2);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setCellPhones(cellPhones);

        // Act
        List<Product> result = productResponse.getCellPhones();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Phone 1", result.get(0).getName());
        assertEquals("Phone 2", result.get(1).getName());
    }

    @Test
    void testGetCoffeeMakers() {
        // Arrange
        Product coffeeMaker1 = new Product.Builder()
                .name("Coffee Maker 1")
                .price(100.0)
                .description("Coffee Maker Description 1")
                .build();

        List<Product> coffeeMakers = Arrays.asList(coffeeMaker1);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setCoffeeMakers(coffeeMakers);

        // Act
        List<Product> result = productResponse.getCoffeeMakers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Coffee Maker 1", result.get(0).getName());
    }

    @Test
    void testGetAirConditioning() {
        // Arrange
        Product airConditioner = new Product.Builder()
                .name("Air Conditioner")
                .price(500.0)
                .description("Cool air")
                .build();

        List<Product> airConditioning = Arrays.asList(airConditioner);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setAirConditioning(airConditioning);

        // Act
        List<Product> result = productResponse.getAirConditioning();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Air Conditioner", result.get(0).getName());
    }

    @Test
    void testGetOutstanding() {
        // Arrange
        Product outstandingProduct = new Product.Builder()
                .name("Outstanding Product")
                .price(1500.0)
                .description("Top product")
                .build();

        List<Product> outstanding = Arrays.asList(outstandingProduct);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setOutstanding(outstanding);

        // Act
        List<Product> result = productResponse.getOutstanding();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Outstanding Product", result.get(0).getName());
    }


}
