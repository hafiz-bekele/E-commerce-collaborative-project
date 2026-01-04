package com.ecommerce.model;

import java.util.*;

public class Order {
    private String orderId;
    private int userId;
    private Date orderDate;
    private String status; // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    private List<OrderItem> items;
    private double totalAmount;
    private String shippingAddress;
    
    public Order(String orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = new Date();
        this.status = "PENDING";
        this.items = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getOrderId() { return orderId; }
    public int getUserId() { return userId; }
    public Date getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    
    public void addItem(OrderItem item) {
        items.add(item);
    }
    
    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    
    public boolean cancelOrder() {
        if (status.equals("PENDING") || status.equals("CONFIRMED")) {
            status = "CANCELLED";
            return true;
        }
        return false;
    }
}