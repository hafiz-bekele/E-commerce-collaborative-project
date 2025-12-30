import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private int customerId;
    private Address shippingAddress;           // Association to Address
    private Address billingAddress;            // Association to Address
    private int loyaltyPoints;
    private Preferences preferences;           // Association to Preferences
    private ShoppingCart shoppingCart;         // Composition (1:1)
    private List<Order> orders;                // Composition (1:N)
    private List<Review> reviews;              // Composition (1:N)
    
    public Customer() {
        super();
        this.loyaltyPoints = 0;
        this.shoppingCart = new ShoppingCart();
        this.orders = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }
    
    public Customer(String username, String email, String password) {
        super(username, email, password);
        this.loyaltyPoints = 0;
        this.shoppingCart = new ShoppingCart();
        this.orders = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { 
        this.customerId = customerId; 
        if (this.shoppingCart != null) {
            this.shoppingCart.setCustomerId(customerId);
        }
    }
    
    public Address getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(Address shippingAddress) { 
        this.shippingAddress = shippingAddress; 
    }
    
    public Address getBillingAddress() { return billingAddress; }
    public void setBillingAddress(Address billingAddress) { 
        this.billingAddress = billingAddress; 
    }
    
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { 
        this.loyaltyPoints = loyaltyPoints; 
    }
    
    public Preferences getPreferences() { return preferences; }
    public void setPreferences(Preferences preferences) { 
        this.preferences = preferences; 
    }
    
    public ShoppingCart getShoppingCart() { return shoppingCart; }
    public void setShoppingCart(ShoppingCart shoppingCart) { 
        this.shoppingCart = shoppingCart;
        if (shoppingCart != null) {
            shoppingCart.setCustomerId(this.customerId);
        }
    }
    
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
    
    // Implement abstract methods
    @Override
    public boolean login(String email, String password) {
        System.out.println("Customer login attempted: " + email);
        return getEmail().equals(email) && verifyPassword(password);
    }
    
    @Override
    public void logout() {
        System.out.println("Customer logged out: " + getUsername());
    }
    
    // Business methods with relationships
    public Order placeOrder() {
        Order order = new Order();
        order.setCustomerId(this.customerId);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(this.shoppingCart.calculateTotal());
        
        // Add to orders list (maintains relationship)
        this.orders.add(order);
        
        System.out.println("Order placed by customer: " + getUsername());
        return order;
    }
    
    public void addToCart(Product product, int quantity) {
        if (this.shoppingCart != null) {
            this.shoppingCart.addItem(product.getProductId(), quantity);
            System.out.println("Added " + quantity + " of " + product.getName() + " to cart");
        }
    }
    
    public Review writeReview(Product product, int rating, String comment) {
        Review review = new Review();
        review.setProductId(product.getProductId());
        review.setCustomerId(this.customerId);
        review.setRating(rating);
        review.setComment(comment);
        review.setDate(new Date());
        review.setVerifiedPurchase(true);
        
        // Add to reviews list (maintains relationship)
        this.reviews.add(review);
        
        System.out.println("Review written for product: " + product.getName());
        return review;
    }
    
    // Relationship management methods
    public void addOrder(Order order) {
        if (order != null) {
            order.setCustomerId(this.customerId);
            this.orders.add(order);
        }
    }
    
    public void addReview(Review review) {
        if (review != null) {
            review.setCustomerId(this.customerId);
            this.reviews.add(review);
        }
    }
}