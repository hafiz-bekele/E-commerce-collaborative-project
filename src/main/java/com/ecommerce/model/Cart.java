package com.ecommerce.model;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items = new ArrayList<>();
    
    public void addItem(Product p) { items.add(p); }
    public void removeItem(Product p) { items.remove(p); }
    public List<Product> getItems() { return items; }
    
    public double getTotal() {
        double total = 0;
        for (Product p : items) total += p.getPrice();
        return total;
    }
    
    public String toString() {
        return "Cart with " + items.size() + " items. Total: $" + getTotal();
    }
}
