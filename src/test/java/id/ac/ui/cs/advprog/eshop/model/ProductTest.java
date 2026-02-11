package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testSetAndGetProductId() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        product.setProductId(productId);
        assertEquals(productId, product.getProductId());
    }

    @Test
    void testSetAndGetProductName() {
        String productName = "Sampo Cap Bambang";
        product.setProductName(productName);
        assertEquals(productName, product.getProductName());
    }

    @Test
    void testSetAndGetProductQuantity() {
        int quantity = 100;
        product.setProductQuantity(quantity);
        assertEquals(quantity, product.getProductQuantity());
    }

    @Test
    void testAllFieldsTogether() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String productName = "Sampo Cap Bambang";
        int quantity = 100;

        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(quantity);

        assertEquals(productId, product.getProductId());
        assertEquals(productName, product.getProductName());
        assertEquals(quantity, product.getProductQuantity());
    }
}
