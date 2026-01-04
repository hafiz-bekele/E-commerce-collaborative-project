public class TestCart {
    public static void main(String[] args) {
        System.out.println("=== SHOPPING CART TEST ===");
        
        com.ecommerce.cart.CartService service = new com.ecommerce.cart.CartService();
        
        // Test 1: Add items to cart
        System.out.println("\n1. Adding items to cart:");
        service.addToCart(101, 1, "Laptop", 999.99, 1);
        service.addToCart(101, 2, "Phone", 499.99, 2);
        
        // Test 2: Get cart total
        double total = service.getCartTotal(101);
        System.out.println("\n2. Cart Total: $" + total);
        
        // Test 3: Get cart items
        System.out.println("\n3. Cart Items:");
        for (com.ecommerce.model.CartItem item : service.getCartItems(101)) {
            System.out.println("   " + item.getProductName() + " x" + item.getQuantity());
        }
        
        // Test 4: Update quantity
        System.out.println("\n4. Update quantity:");
        service.updateQuantity(101, 2, 1);
        total = service.getCartTotal(101);
        System.out.println("   New total: $" + total);
        
        System.out.println("\n✅ Cart tests completed!");
    }
}