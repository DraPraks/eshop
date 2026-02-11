package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
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
    void testCreate() {
        doNothing().when(productRepository).create(any(Product.class));

        Product result = productService.create(product1);

        assertNotNull(result);
        assertEquals(product1.getProductId(), result.getProductId());
        assertEquals(product1.getProductName(), result.getProductName());
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAll() {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        
        Iterator<Product> iterator = products.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product1.getProductId(), result.get(0).getProductId());
        assertEquals(product2.getProductId(), result.get(1).getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById(anyString())).thenReturn(product1);

        Product result = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(result);
        assertEquals(product1.getProductId(), result.getProductId());
        assertEquals(product1.getProductName(), result.getProductName());
        verify(productRepository, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Baru");
        updatedProduct.setProductQuantity(200);

        when(productRepository.update(anyString(), any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Baru", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        verify(productRepository, times(1)).update("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct);
    }

    @Test
    void testDelete() {
        when(productRepository.delete(anyString())).thenReturn(true);

        boolean result = productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertTrue(result);
        verify(productRepository, times(1)).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}
