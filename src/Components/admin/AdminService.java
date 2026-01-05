package com.ecommerce.admin;

import com.ecommerce.model.*;
import java.util.*;

public class AdminService {
    private List<User> allUsers;
    private List<com.ecommerce.model.Order> allOrders;
    
    public AdminService() {
        allUsers = new ArrayList<>();
        allOrders = new ArrayList<>();
    }
    
    public void addUser(User user) {
        allUsers.add(user);
    }
    
    public void addOrder(com.ecommerce.model.Order order) {
        allOrders.add(order);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(allUsers);
    }
    
    public List<com.ecommerce.model.Order> getAllOrders() {
        return new ArrayList<>(allOrders);
    }
    
    public Map<String, Integer> getSalesReport() {
        Map<String, Integer> report = new HashMap<>();
        report.put("TOTAL_ORDERS", allOrders.size());
        
        int pending = 0, completed = 0, cancelled = 0;
        for (com.ecommerce.model.Order order : allOrders) {
            switch (order.getStatus()) {
                case "PENDING": pending++; break;
                case "CONFIRMED": 
                case "SHIPPED": 
                case "DELIVERED": completed++; break;
                case "CANCELLED": cancelled++; break;
            }
        }
        
        report.put("PENDING", pending);
        report.put("COMPLETED", completed);
        report.put("CANCELLED", cancelled);
        
        return report;
    }
    
    public boolean deactivateUser(int userId) {
        for (User user : allUsers) {
            // Find user and deactivate
            user.setActive(false);
            return true;
        }
        return false;
    }
}