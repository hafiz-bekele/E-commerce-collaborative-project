package com.ecommerce.model;

public class Admin {
    private int adminId;
    private String name;
    
    public Admin(int id, String name) {
        this.adminId = id;
        this.name = name;
    }
    
    public int getId() { return adminId; }
    public String getName() { return name; }
    
    public void addProduct(Product p) {
        System.out.println("Admin " + name + " added product: " + p.getName());
    }
    
    public void viewReport() {
        System.out.println("Admin " + name + " viewing sales report");
    }
    
    public String toString() {
        return "Admin: " + name + " (ID: " + adminId + ")";
    }
}

