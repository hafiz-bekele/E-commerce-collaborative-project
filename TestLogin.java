/**
 * Test file for Login Module
 * Created by: [hafiz bekele]
 * Branch: hafiz
 * Date: [Today's Date]
 */
public class TestLogin {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("LOGIN MODULE TEST - E-COMMERCE PROJECT");
        System.out.println("Contributor: [Your Name]");
        System.out.println("Student ID: [Your ID]");
        System.out.println("=========================================\n");
        
        try {
            // Create login service
            com.ecommerce.auth.LoginService loginService = new com.ecommerce.auth.LoginService();
            
            System.out.println("1. TESTING EXISTING USER LOGIN");
            System.out.println("-------------------------------");
            
            // Test 1: Login with admin
            System.out.print("   Admin login... ");
            com.ecommerce.model.User admin = loginService.login("admin@example.com", "admin123");
            System.out.println("✓ SUCCESS: " + admin);
            loginService.logout(admin);
            
            // Test 2: Login with customer
            System.out.print("   Customer login... ");
            com.ecommerce.model.User customer = loginService.login("john@example.com", "password123");
            System.out.println("✓ SUCCESS: " + customer);
            
            System.out.println("\n2. TESTING USER REGISTRATION");
            System.out.println("----------------------------");
            
            // Test 3: Register new user
            System.out.print("   Register new user... ");
            com.ecommerce.model.User newUser = loginService.register("testuser", "test@example.com", "TestPass123");
            System.out.println("✓ SUCCESS: " + newUser);
            
            // Test 4: Login with new user
            System.out.print("   Login with new user... ");
            com.ecommerce.model.User loggedInUser = loginService.login("test@example.com", "TestPass123");
            System.out.println("✓ SUCCESS: " + loggedInUser);
            
            System.out.println("\n3. TESTING ERROR CASES");
            System.out.println("----------------------");
            
            // Test 5: Wrong password
            System.out.print("   Wrong password test... ");
            try {
                loginService.login("admin@example.com", "wrongpass");
                System.out.println("✗ FAILED: Should have thrown error");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ PASSED: " + e.getMessage());
            }
            
            // Test 6: Non-existent user
            System.out.print("   Non-existent user test... ");
            try {
                loginService.login("nonexistent@example.com", "password");
                System.out.println("✗ FAILED: Should have thrown error");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ PASSED: " + e.getMessage());
            }
            
            // Test 7: Duplicate registration
            System.out.print("   Duplicate registration test... ");
            try {
                loginService.register("duplicate", "admin@example.com", "password");
                System.out.println("✗ FAILED: Should have thrown error");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ PASSED: " + e.getMessage());
            }
            
            System.out.println("\n4. TESTING PASSWORD CHANGE");
            System.out.println("--------------------------");
            
            // Test 8: Change password
            System.out.print("   Changing password... ");
            boolean changed = loginService.changePassword("test@example.com", "TestPass123", "NewPassword456");
            if (changed) {
                System.out.println("✓ SUCCESS: Password changed");
                
                // Test login with new password
                System.out.print("   Login with new password... ");
                com.ecommerce.model.User userWithNewPass = loginService.login("test@example.com", "NewPassword456");
                System.out.println("✓ SUCCESS: " + userWithNewPass);
            } else {
                System.out.println("✗ FAILED: Password change failed");
            }
            
            System.out.println("\n=========================================");
            System.out.println("TEST SUMMARY:");
            System.out.println("Total users in system: " + loginService.getUserCount());
            System.out.println("All tests completed!");
            System.out.println("=========================================");
            
        } catch (Exception e) {
            System.err.println("\nTEST FAILED WITH ERROR:");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}