import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private int userId;
    private String username;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private Date createdAt;
    private boolean isActive;
    
    // === RELATIONSHIP FIELDS ADDED ===
    private List<Address> addresses;           // 1-to-Many Composition
    private List<Review> reviewsWritten;       // 1-to-Many Association (through Customer)
    // =================================
    
    // Constructor
    public User(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdAt = new Date();
        this.isActive = true;
        this.phoneNumber = "";
        this.passwordHash = "";
        
        // Initialize relationship collections
        this.addresses = new ArrayList<>();
        this.reviewsWritten = new ArrayList<>();
    }
    
    // Default constructor
    public User() {
        this(0, "Guest", "guest@example.com");
    }
    
    // Getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public Date getCreatedAt() { return createdAt; }
    public boolean isActive() { return isActive; }
    
    // === RELATIONSHIP GETTERS ===
    public List<Address> getAddresses() { return addresses; }
    public List<Review> getReviewsWritten() { return reviewsWritten; }
    
    // Setters
    public void setUserId(int userId) { 
        if (userId > 0) this.userId = userId; 
    }
    
    public void setUsername(String username) { 
        if (username != null) this.username = username; 
    }
    
    public void setEmail(String email) { 
        if (email != null && email.contains("@")) this.email = email; 
    }
    
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    
    public void setActive(boolean active) { 
        this.isActive = active; 
    }
    
    // === RELATIONSHIP SETTERS ===
    public void setAddresses(List<Address> addresses) { 
        this.addresses = addresses; 
    }
    
    public void setReviewsWritten(List<Review> reviewsWritten) { 
        this.reviewsWritten = reviewsWritten; 
    }
    
    // Relationship management methods
    public void addAddress(Address address) {
        if (address != null && !addresses.contains(address)) {
            addresses.add(address);
        }
    }
    
    public void addReviewWritten(Review review) {
        if (review != null && !reviewsWritten.contains(review)) {
            reviewsWritten.add(review);
        }
    }
    
    // Password handling
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }
    
    private String hashPassword(String plainText) {
        return "hashed_" + plainText;
    }
    
    // Abstract methods
    public abstract boolean login(String email, String password);
    public abstract void logout();
    
    // Concrete methods
    public boolean updateProfile(String newUsername, String newPhone) {
        boolean updated = false;
        if (newUsername != null) {
            this.username = newUsername;
            updated = true;
        }
        if (newPhone != null) {
            this.phoneNumber = newPhone;
            updated = true;
        }
        return updated;
    }
    
    // Helper method
    public String getUserInfo() {
        return "User ID: " + userId + ", Username: " + username + 
               ", Email: " + email + ", Addresses: " + addresses.size();
    }
}