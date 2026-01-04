package com.ecommerce.service;

import com.ecommerce.model.Product;
import java.util.*;

public class ProductService {
    private Map<Integer, Product> productDatabase;
    private int nextProductId;
    
    public ProductService() {
        productDatabase = new HashMap<>();
        nextProductId = 1;
        initializeSampleProducts();
    }
    
    private void initializeSampleProducts() {
        addProduct(new Product(nextProductId, "Laptop", 999.99, 10));
        addProduct(new Product(nextProductId, "Smartphone", 499.99, 25));
        addProduct(new Product(nextProductId, "Headphones", 89.99, 50));
        addProduct(new Product(nextProductId, "Keyboard", 49.99, 30));
    }
    
    public Product addProduct(Product product) {
        product.setId(nextProductId++);
        productDatabase.put(product.getId(), product);
        System.out.println("✅ Product added: " + product.getName());
        return product;
    }
    
    public Product getProductById(int productId) {
        Product p = productDatabase.get(productId);
        if (p == null) {
            System.out.println("❌ Product ID " + productId + " not found");
        }
        return p;
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDatabase.values());
    }
    
    public List<Product> searchProducts(String keyword) {
        List<Product> results = new ArrayList<>();
        String searchTerm = keyword.toLowerCase();
        
        for (Product product : productDatabase.values()) {
            if (product.getName().toLowerCase().contains(searchTerm)) {
                results.add(product);
            }
        }
        return results;
    }
    
    public boolean updateProductStock(int productId, int newStock) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setStock(newStock);
            System.out.println("📦 Updated product " + productId + " stock to: " + newStock);
            return true;
        }
        return false;
    }
    
    public boolean deleteProduct(int productId) {
        Product removed = productDatabase.remove(productId);
        if (removed != null) {
            System.out.println("🗑️ Deleted product: " + removed.getName());
            return true;
        }
        return false;
    }
    
    public void displayAllProducts() {
        System.out.println("\n=== PRODUCT CATALOG ===");
        System.out.println("Total products: " + productDatabase.size());
        System.out.println("-----------------------");
        
        for (Product product : productDatabase.values()) {
            System.out.println("ID: " + product.getId() + 
                             " | " + product.getName() + 
                             " | $" + product.getPrice() + 
                             " | Stock: " + product.getStock());
        }
    }
    
    public int getProductCount() {
        return productDatabase.size();
    }
}
