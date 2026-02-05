package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
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
    private final List<Product> productData = new ArrayList<>();

    /**
     * Creates and adds a new product to the repository.
     *
     * @param product the product to be added
     */
    public void create(Product product) {
        productData.add(product);
    }

    /**
     * Returns an iterator over all products in the repository.
     * @return an iterator for all products
     */
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    /**
     * Finds a product by its ID.
     *
     * @param productId the ID of the product to find
     * @return the product with the given ID, or null if not found
     */
    public Product findById(String productId) {
        for (Product product : productData) {
            if (productId != null && productId.equals(product.getProductId())) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates an existing product in the repository.
     *
     * @param productId the ID of the product to update
     * @param updatedProduct the new product data
     * @return the updated product, or null if the product to update was not found
     */
    public Product update(String productId, Product updatedProduct) {
        Product existing = findById(productId);
        if (existing == null) {
            return null;
        }
        existing.setProductName(updatedProduct.getProductName());
        existing.setProductQuantity(updatedProduct.getProductQuantity());
        return existing;
    }
}
