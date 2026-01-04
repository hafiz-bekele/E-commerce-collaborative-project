package com.ecommerce.model;

public class CartItem {
    private Product product;
    private int quantity;
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    
    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    public void increaseQuantity(int amount) {
        quantity += amount;
    }
    
    public void decreaseQuantity(int amount) {
        if (quantity - amount >= 0) {
            quantity -= amount;
        }
    }
}
