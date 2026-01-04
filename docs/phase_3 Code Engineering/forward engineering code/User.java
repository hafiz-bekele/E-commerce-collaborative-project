import java.util.Date;

public abstract class User {
    private int userId;
    private String username;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private Date createdAt;
    private boolean isActive;
    
    public User() {
        this.createdAt = new Date();
        this.isActive = true;
    }
    
    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        setPassword(password);
    }
    
    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public Date getCreatedAt() { return createdAt; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
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
        this.username = newUsername;
        this.phoneNumber = newPhone;
        return true;
    }
    
    public boolean changePassword(String oldPassword, String newPassword) {
        if (verifyPassword(oldPassword)) {
            setPassword(newPassword);
            return true;
        }
        return false;
    }
    
    private boolean verifyPassword(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }
}