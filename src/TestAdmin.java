public class TestAdmin {
    public static void main(String[] args) {
        System.out.println("=== ADMIN & PAYMENT TEST ===");
        
        // Test Payment
        System.out.println("\n1. Payment Service Test:");
        com.ecommerce.payment.PaymentService payment = new com.ecommerce.payment.PaymentService();
        boolean paymentResult = payment.processPayment("ORD1001", 1499.97, "CREDIT_CARD");
        System.out.println("   Payment result: " + paymentResult);
        
        // Test Admin
        System.out.println("\n2. Admin Service Test:");
        com.ecommerce.admin.AdminService admin = new com.ecommerce.admin.AdminService();
        
        // Add sample data
        admin.addUser(new com.ecommerce.model.User("user1", "user1@test.com", "pass123"));
        admin.addUser(new com.ecommerce.model.User("user2", "user2@test.com", "pass456"));
        
        System.out.println("   Total users: " + admin.getAllUsers().size());
        
        // Generate report
        System.out.println("\n3. Sales Report:");
        java.util.Map<String, Integer> report = admin.getSalesReport();
        for (java.util.Map.Entry<String, Integer> entry : report.entrySet()) {
            System.out.println("   " + entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("\n✅ Admin & Payment tests completed!");
    }
}