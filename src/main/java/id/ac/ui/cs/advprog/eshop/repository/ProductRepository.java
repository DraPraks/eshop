package id.ac.ui.cs.advprog.eshop.repository;

import id.ui.ac.id.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Repository for managing Product entities.
 * Provides basic CRUD operations for products in memory.
 */
@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    /**
     * Creates and adds a new product to the repository.
     * @param product the product to be added
     * @return the added product
     */
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    /**
     * Returns an iterator over all products in the repository.
     * @return an iterator for all products
     */
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
