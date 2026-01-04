package com.ecommerce.service;

public class ProductValidator {
    public static boolean validatePrice(double price) {
        return price > 0;
    }
    
    public static boolean validateStock(int stock) {
        return stock >= 0;
    }
}
