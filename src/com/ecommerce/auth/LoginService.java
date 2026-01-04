package com.ecommerce.auth;

import com.ecommerce.model.User;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private Map<String, User> userDatabase;
    
    public LoginService() {
        userDatabase = new HashMap<>();
        initializeSampleUsers();
    }
    
    private void initializeSampleUsers() {
        // Create admin user
        User admin = new User("admin", "admin@example.com", "admin123");
        userDatabase.put(admin.getEmail(), admin);
        
        // Create customer user
        User customer = new User("john", "john@example.com", "password123");
        userDatabase.put(customer.getEmail(), customer);
        
        // Create seller user
        User seller = new User("sara", "sara@example.com", "seller456");
        userDatabase.put(seller.getEmail(), seller);
    }
    
    /**
     * Authenticate user with email and password
     */
    public User login(String email, String password) {
        // Input validation
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        // Check if user exists
        User user = userDatabase.get(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + email);
        }
        
        // Check password
        if (!user.login(email, password)) {
            throw new IllegalArgumentException("Invalid password for: " + email);
        }
        
        // Check if account is active
        if (!user.isActive()) {
            throw new IllegalArgumentException("Account is deactivated: " + email);
        }
        
        System.out.println("Login successful for: " + email);
        return user;
    }
    
    /**
     * Register new user
     */
    public User register(String username, String email, String password) {
        // Validation
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        
        if (email == null || !isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        
        // Check if user already exists
        if (userDatabase.containsKey(email)) {
            throw new IllegalArgumentException("User already exists: " + email);
        }
        
        // Create and save new user
        User newUser = new User(username, email, password);
        userDatabase.put(email, newUser);
        
        System.out.println("User registered successfully: " + newUser);
        return newUser;
    }
    
    /**
     * Logout user
     */
    public void logout(User user) {
        if (user != null) {
            user.logout();
            System.out.println("User logged out: " + user.getUsername());
        }
    }
    
    /**
     * Change user password
     */
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        try {
            // First authenticate with old password
            User user = login(email, oldPassword);
            
            // Change password
            user.setPassword(newPassword);
            System.out.println("Password changed successfully for: " + email);
            return true;
            
        } catch (Exception e) {
            System.out.println("Password change failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Check if email is valid format
     */
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    /**
     * Get user by email (for testing)
     */
    public User getUserByEmail(String email) {
        return userDatabase.get(email);
    }
    
    /**
     * Get total users count (for testing)
     */
    public int getUserCount() {
        return userDatabase.size();
    }
}