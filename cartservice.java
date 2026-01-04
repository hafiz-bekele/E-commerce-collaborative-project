package com.ecommerce.cart;

import com.ecommerce.model.*;
import java.util.*;public class CartService {
    private Map<Integer, Cart> userCarts;
    
    public CartService() {
        userCarts = new HashMap<>();
    }
    
    public Cart getOrCreateCart(int userId) {
        return userCarts.computeIfAbsent(userId, k -> new Cart(userId));
    }
    
    public Cart addToCart(int userId, int productId, String productName, double price, int quantity) {
        Cart cart = getOrCreateCart(userId);
        CartItem item = new CartItem(productId, productName, price, quantity);
        cart.addItem(item);
        System.out.println("Added to cart: " + productName + " x" + quantity);
        return cart;
    }
    
    public boolean removeFromCart(int userId, int productId) {
        Cart cart = userCarts.get(userId);
        if (cart != null) {
            return cart.removeItem(productId);
        }
        return false;
    }
    
    public Cart updateQuantity(int userId, int productId, int newQuantity) {
        Cart cart = userCarts.get(userId);
        if (cart != null) {
            for (CartItem item : cart.getItems()) {
                if (item.getProductId() == productId) {
                    item.setQuantity(newQuantity);
                    break;
                }
            }
        }
        return cart;
    }
    
    public double getCartTotal(int userId) {
        Cart cart = userCarts.get(userId);
        return cart != null ? cart.calculateTotal() : 0;
    }
    
    public void clearCart(int userId) {
        Cart cart = userCarts.get(userId);
        if (cart != null) {
            cart.clear();
        }
    }
    
    public List<CartItem> getCartItems(int userId) {
        Cart cart = userCarts.get(userId);
        return cart != null ? cart.getItems() : new ArrayList<>();
    }
}