import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {
    private int cartId;
    private int customerId;
    private Date createdAt;
    private Date updatedAt;
    private List<CartItem> items;  // Composition (1:N)
    
    public ShoppingCart() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.items = new ArrayList<>();
    }
    
    public ShoppingCart(int customerId) {
        this();
        this.customerId = customerId;
    }
    
    // Getters and Setters
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
    
    // Business methods with relationship management
    public void addItem(int productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                this.updatedAt = new Date();
                System.out.println("Updated quantity for product: " + productId);
                return;
            }
        }
        
        CartItem newItem = new CartItem();
        newItem.setProductId(productId);
        newItem.setQuantity(quantity);
        newItem.setAddedDate(new Date());
        items.add(newItem);
        this.updatedAt = new Date();
        System.out.println("Added product: " + productId + " to cart");
    }
    
    public void removeItem(int productId) {
        items.removeIf(item -> item.getProductId() == productId);
        this.updatedAt = new Date();
        System.out.println("Removed product: " + productId + " from cart");
    }
    
    public void updateQuantity(int productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProductId() == productId) {
                item.setQuantity(quantity);
                this.updatedAt = new Date();
                System.out.println("Updated quantity for product: " + productId + " to " + quantity);
                return;
            }
        }
        System.out.println("Product not found in cart: " + productId);
    }
    
    public double calculateTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getQuantity() * 10.0; // Example price
        }
        return total;
    }
    
    public void clearCart() {
        items.clear();
        this.updatedAt = new Date();
        System.out.println("Cart cleared");
    }
    
    public Order checkout() {
        Order order = new Order();
        order.setCustomerId(this.customerId);
        order.setTotalAmount(calculateTotal());
        order.setStatus(OrderStatus.PENDING);
        
        System.out.println("Checkout completed for cart: " + cartId);
        clearCart();
        return order;
    }
}