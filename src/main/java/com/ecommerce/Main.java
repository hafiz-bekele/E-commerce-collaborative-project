package com.ecommerce;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

public class Main {
    public static void main(String[] args) {
        System.out.println("🛒 E-COMMERCE SYSTEM - PRODUCT CATALOG DEMO\n");
        
        // Initialize product service
        ProductService productService = new ProductService();
        
        // Display all products
        productService.displayAllProducts();
        
        // Demonstrate product search
        System.out.println("\n🔍 SEARCH RESULTS for 'phone':");
        for (Product p : productService.searchProducts("phone")) {
            System.out.println("   - " + p.getName() + " ($" + p.getPrice() + ")");
        }
        
        // Check product availability
        System.out.println("\n📦 PRODUCT AVAILABILITY CHECK:");
        Product laptop = productService.getProductById(1);
        if (laptop != null) {
            System.out.println("   Product: " + laptop.getName());
            System.out.println("   Price: $" + laptop.getPrice());
            System.out.println("   In Stock: " + laptop.isAvailable());
            System.out.println("   Stock Qty: " + laptop.getStock());
        }
        
        // Add a new product
        System.out.println("\n➕ ADDING NEW PRODUCT:");
        Product tablet = new Product(5, "Tablet", 329.99, 8);
        productService.addProduct(tablet);
        
        // Final count
        System.out.println("\n📊 FINAL STATISTICS:");
        System.out.println("   Total Products: " + productService.getProductCount());
        
        System.out.println("\n✨ DEMO COMPLETED SUCCESSFULLY!");
    }
}
