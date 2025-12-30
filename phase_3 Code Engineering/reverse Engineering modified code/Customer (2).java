import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Customer extends User {
    private int customerId;
    
    // === RELATIONSHIP FIELDS ===
    private ShoppingCart shoppingCart;         // 1-to-1 Composition
    private List<Order> orders;                // 1-to-Many Composition
    private List<Product> wishlist;            // Many-to-Many Association
    // Note: reviewsWritten inherited from User
    // Note: addresses inherited from User
    // ============================
    
    // Constructor
    public Customer(int customerId, String username, String email) {
        super(0, username, email);
        this.customerId = customerId;
        
        // Initialize relationship collections
        this.shoppingCart = new ShoppingCart(customerId);
        this.orders = new ArrayList<>();
        this.wishlist = new ArrayList<>();
    }
    
    // Default constructor
    public Customer() {
        super();
        this.customerId = 0;
        this.shoppingCart = new ShoppingCart(0);
        this.orders = new ArrayList<>();
        this.wishlist = new ArrayList<>();
    }
    
    // Getters
    public int getCustomerId() { return customerId; }
    public ShoppingCart getShoppingCart() { return shoppingCart; }
    public List<Order> getOrders() { return orders; }
    public List<Product> getWishlist() { return wishlist; }
    
    // Setters
    public void setCustomerId(int customerId) { 
        this.customerId = customerId; 
        if (this.shoppingCart != null) {
            this.shoppingCart.setCustomerId(customerId);
        }
    }
    
    public void setShoppingCart(ShoppingCart shoppingCart) { 
        this.shoppingCart = shoppingCart; 
    }
    
    public void setOrders(List<Order> orders) { this.orders = orders; }
    public void setWishlist(List<Product> wishlist) { this.wishlist = wishlist; }
    
    // Implement abstract methods
    @Override
    public boolean login(String email, String password) {
        System.out.println("Customer " + getUsername() + " attempting login...");
        boolean success = getEmail().equals(email);
        if (success) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }
        return success;
    }
    
    @Override
    public void logout() {
        System.out.println("Customer " + getUsername() + " logged out.");
    }
    
    // Relationship management methods
    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
            order.setCustomerId(this.customerId);
        }
    }
    
    public void addToWishlist(Product product) {
        if (product != null && !wishlist.contains(product)) {
            wishlist.add(product);
            product.addCustomerToWishlist(this);
        }
    }
    
    // Business methods
    public Order placeOrder() {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setStatus(OrderStatus.PENDING);
        addOrder(order);
        System.out.println("Order placed by customer: " + getUsername());
        return order;
    }
    
    // Helper method
    public String getCustomerInfo() {
        return "Customer ID: " + customerId + ", " + super.getUserInfo() + 
               ", Orders: " + orders.size() + ", Wishlist: " + wishlist.size() +
               ", Cart: " + (shoppingCart != null ? "Has cart" : "No cart");
    }
}