package com.ecommerce.service;

import com.ecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList;
    
    public ProductService() {
        productList = new ArrayList<>();
        productList.add(new Product(1, "Laptop", 999.99, 10));
        productList.add(new Product(2, "Mouse", 19.99, 50));
        productList.add(new Product(3, "Keyboard", 49.99, 30));
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }
    
    public Product getProductById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public void addProduct(Product product) {
        productList.add(product);
    }
    
    public boolean updateStock(int productId, int newStock) {
        Product product = getProductById(productId);
        if (product != null) {
            product.setStock(newStock);
            return true;
        }
        return false;
    }
}
