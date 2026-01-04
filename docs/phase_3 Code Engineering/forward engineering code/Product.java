
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private double discountPrice;
    private int stockQuantity;
    private int categoryId;
    private int sellerId;
    private int ecoScore;
    private List<String> images;
    private Map<String, String> specifications;
    private Date createdAt;
    private Date updatedAt;
    
    // === RELATIONSHIP FIELDS ADDED ===
    private List<Review> reviews;              // Product has-many Reviews
    private List<Order> orders;                // Product has-many Orders (through OrderItems)
    // =================================
    
    public Product() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.images = new ArrayList<>();
        this.specifications = new HashMap<>();
        this.reviews = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
    
    public Product(String name, String description, double price, int stock) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stock;
        this.discountPrice = price; // Initially same as price
    }
    
    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    
    public String getName() { return name; }
    public void setName(String name) { 
        this.name = name; 
        this.updatedAt = new Date();
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.updatedAt = new Date();
    }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { 
        this.price = price; 
        this.updatedAt = new Date();
    }
    
    public double getDiscountPrice() { return discountPrice; }
    public void setDiscountPrice(double discountPrice) { 
        this.discountPrice = discountPrice; 
        this.updatedAt = new Date();
    }
    
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { 
        this.stockQuantity = stockQuantity; 
        this.updatedAt = new Date();
    }
    
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    
    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
    
    public int getEcoScore() { return ecoScore; }
    public void setEcoScore(int ecoScore) { this.ecoScore = ecoScore; }
    
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    
    public Map<String, String> getSpecifications() { return specifications; }
    public void setSpecifications(Map<String, String> specifications) { 
        this.specifications = specifications; 
    }
    
    public Date getCreatedAt() { return createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    
    // === RELATIONSHIP GETTERS AND SETTERS ===
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
    
    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
    
    // Business methods
    public boolean isAvailable() {
        return stockQuantity > 0;
    }
    
    public boolean reduceStock(int quantity) {

if (quantity <= 0 || quantity > stockQuantity) {
            return false;
        }
        this.stockQuantity -= quantity;
        this.updatedAt = new Date();
        return true;
    }
    
    public double calculateFinalPrice() {
        if (discountPrice < price && discountPrice > 0) {
            return discountPrice;
        }
        return price;
    }
    
    public double getAverageRating() {
        // In real implementation, calculate from reviews
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }
    
    public boolean updateProduct(Map<String, Object> updates) {
        try {
            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                switch (entry.getKey()) {
                    case "name":
                        setName((String) entry.getValue());
                        break;
                    case "price":
                        setPrice((Double) entry.getValue());
                        break;
                    case "description":
                        setDescription((String) entry.getValue());
                        break;
                    case "stockQuantity":
                        setStockQuantity((Integer) entry.getValue());
                        break;
                    case "discountPrice":
                        setDiscountPrice((Double) entry.getValue());
                        break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // === RELATIONSHIP METHODS ===
    public void addReview(Review review) {
        if (review != null && !reviews.contains(review)) {
            reviews.add(review);
            review.setProductId(this.productId);
        }
    }
    
    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }
}