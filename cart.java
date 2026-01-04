package com.ecommerce.model;

import java.util.*;

public class Cart {
    private int cartId;
    private int userId;
    private List<CartItem> items;
    private Date createdAt;
    
    public Cart(int userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
        this.createdAt = new Date();
    }
    
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public List<CartItem> getItems() { return items; }
    public Date getCreatedAt() { return createdAt; }
    
    public void addItem(CartItem item) {
        items.add(item);
    }
    
    public boolean removeItem(int productId) {
        return items.removeIf(item -> item.getProductId() == productId);
    }
    
    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    public void clear() {
        items.clear();
    }
    
    public int getItemCount() {
        return items.size();
    }
}