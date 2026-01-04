package com.ecommerce.model;
import java.util.Date;

public class Order {
    private int orderId;
    private Cart cart;
    private Date orderDate;
    
    public Order(int id, Cart cart) {
        this.orderId = id;
        this.cart = cart;
        this.orderDate = new Date();
    }
    
    public double getTotal() { return cart.getTotal(); }
    public int getId() { return orderId; }
    public Date getDate() { return orderDate; }
    
    public String toString() {
        return "Order #" + orderId + " - Total: $" + getTotal() + " - Date: " + orderDate;
    }
}
