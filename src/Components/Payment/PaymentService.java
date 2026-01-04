package com.ecommerce.payment;

public class PaymentService {
    
    public boolean processPayment(String orderId, double amount, String paymentMethod) {
        System.out.println("Processing payment for order " + orderId);
        System.out.println("Amount: $" + amount);
        System.out.println("Method: " + paymentMethod);
        
        // Simulate payment processing
        try {
            Thread.sleep(1000); // Simulate processing time
            System.out.println("Payment successful!");
            return true;
        } catch (Exception e) {
            System.out.println("Payment failed: " + e.getMessage());
            return false;
        }
    }
    
    public boolean refundPayment(String transactionId, double amount) {
        System.out.println("Processing refund for transaction " + transactionId);
        System.out.println("Amount: $" + amount);
        
        // Simulate refund
        try {
            Thread.sleep(800);
            System.out.println("Refund successful!");
            return true;
        } catch (Exception e) {
            System.out.println("Refund failed: " + e.getMessage());
            return false;
        }
    }
    
    public String generateReceipt(String orderId, double amount) {
        String receipt = "=== RECEIPT ===\n" +
                        "Order ID: " + orderId + "\n" +
                        "Amount: $" + amount + "\n" +
                        "Date: " + new java.util.Date() + "\n" +
                        "Payment Status: COMPLETED\n" +
                        "=== END ===";
        return receipt;
    }
}