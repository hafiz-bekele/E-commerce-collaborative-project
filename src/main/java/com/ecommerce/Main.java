package com.ecommerce;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-commerce System Test ===");
        
        ProductService service = new ProductService();
        List<Product> products = service.getAllProducts();
        
        System.out.println("\nAvailable Products:");
        for (Product p : products) {
            System.out.println("- " + p.getName() + ": $" + p.getPrice());
        }
        
        System.out.println("\n=== Test Complete ===");
    }
}
