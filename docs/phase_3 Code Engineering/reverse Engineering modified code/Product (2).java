import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    
    // === RELATIONSHIP FIELDS ===
    private List<Review> reviews;              // 1-to-Many Association
    private List<Order> orders;                // Many-to-Many Association
    private List<Customer> customersWishlisted; // Many-to-Many Association
    // ============================
    
    // Constructor
    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = "";
        
        // Initialize relationship collections
        this.reviews = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customersWishlisted = new ArrayList<>();
    }
    
    // Default constructor
    public Product() {
        this(0, "Unnamed Product", 0.0, 0);
    }
    
    // Getters
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public List<Review> getReviews() { return reviews; }
    public List<Order> getOrders() { return orders; }
    public List<Customer> getCustomersWishlisted() { return customersWishlisted; }
    
    // Setters
    public void setProductId(int productId) { this.productId = productId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    public void setCustomersWishlisted(List<Customer> customersWishlisted) { 
        this.customersWishlisted = customersWishlisted; 
    }
    
    // Relationship management methods
    public void addReview(Review review) {
        if (review != null && !reviews.contains(review)) {
            reviews.add(review);
            review.setProductId(this.productId);
        }
    }
    
    public void addToOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }
    
    public void addCustomerToWishlist(Customer customer) {
        if (customer != null && !customersWishlisted.contains(customer)) {
            customersWishlisted.add(customer);
        }
    }
    
    // Business methods
    public boolean isAvailable() {
        return stockQuantity > 0;
    }
    
    public boolean reduceStock(int quantity) {
        if (quantity > 0 && quantity <= stockQuantity) {
            stockQuantity -= quantity;
            return true;
        }
        return false;
    }
    
    public String getProductInfo() {
        return "Product: " + name + " (ID: " + productId + 
               "), Price: $" + price + ", Stock: " + stockQuantity +
               ", Reviews: " + reviews.size() + ", Wishlisted by: " + 
               customersWishlisted.size() + " customers";
    }
}