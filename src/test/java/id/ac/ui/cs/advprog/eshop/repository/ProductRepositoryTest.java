package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository repository;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        repository = new ProductRepository();
        
        product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
    }

    @Test
    void testCreateAndFindAll() {
        repository.create(product1);
        repository.create(product2);

        Iterator<Product> productIterator = repository.findAll();
        assertTrue(productIterator.hasNext());
        
        List<Product> products = new ArrayList<>();
        productIterator.forEachRemaining(products::add);
        
        assertEquals(2, products.size());
        assertEquals(product1.getProductId(), products.get(0).getProductId());
        assertEquals(product2.getProductId(), products.get(1).getProductId());
    }

    @Test
    void testFindById() {
        repository.create(product1);
        repository.create(product2);

        Product found = repository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(found);
        assertEquals("Sampo Cap Bambang", found.getProductName());
        assertEquals(100, found.getProductQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        repository.create(product1);
        
        Product found = repository.findById("non-existent-id");
        assertNull(found);
    }

    @Test
    void testFindByIdWithNullId() {
        repository.create(product1);
        
        Product found = repository.findById(null);
        assertNull(found);
    }

    @Test
    void testUpdate() {
        repository.create(product1);

        Product updated = new Product();
        updated.setProductName("Sampo Cap Baru");
        updated.setProductQuantity(200);

        Product result = repository.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updated);
        
        assertNotNull(result);
        assertEquals("Sampo Cap Baru", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
    }

    @Test
    void testUpdateNotFound() {
        Product updated = new Product();
        updated.setProductName("Sampo Cap Baru");
        updated.setProductQuantity(200);

        Product result = repository.update("non-existent-id", updated);
        assertNull(result);
    }

    @Test
    void testDelete() {
        repository.create(product1);
        repository.create(product2);

        boolean deleted = repository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertTrue(deleted);

        Product found = repository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(found);
        
        // Verify product2 is still there
        Product found2 = repository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertNotNull(found2);
    }

    @Test
    void testDeleteNotFound() {
        repository.create(product1);
        
        boolean deleted = repository.delete("non-existent-id");
        assertFalse(deleted);
    }

    @Test
    void testDeleteWithNullId() {
        repository.create(product1);
        
        boolean deleted = repository.delete(null);
        assertFalse(deleted);
    }
}
