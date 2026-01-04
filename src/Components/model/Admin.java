package com.ecommerce.model;

public class Admin extends User {
    private String adminLevel;
    private String department;
    
    public Admin(String username, String email, String password) {
        super(username, email, password);
        this.adminLevel = "STANDARD";
    }
    
    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public boolean manageUser(User user, String action) {
        System.out.println("Admin action: " + action + " on user " + user.getUsername());
        return true;
    }
    
    public void generateReport(String reportType) {
        System.out.println("Generating " + reportType + " report...");
    }
}