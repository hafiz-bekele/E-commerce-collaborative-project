import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private int customerId;
    private Date orderDate;
    private OrderStatus status;
    private double totalAmount;
    private double shippingCost;
    private double taxAmount;
    private String paymentMethod;
    private Address shippingAddress;           // Association to Address
    private Address billingAddress;            // Association to Address
    private String notes;
    private List<OrderItem> orderItems;        // Composition (1:N)
    private Invoice invoice;                   // Composition (1:1)
    
    public Order() {
        this.orderDate = new Date();
        this.status = OrderStatus.PENDING;
        this.shippingCost = 0.0;
        this.taxAmount = 0.0;
        this.totalAmount = 0.0;
        this.orderItems = new ArrayList<>();
        this.orderId = "ORD" + System.currentTimeMillis();
    }
    
    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    
    public double getShippingCost() { return shippingCost; }
    public void setShippingCost(double shippingCost) { this.shippingCost = shippingCost; }
    
    public double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(double taxAmount) { this.taxAmount = taxAmount; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public Address getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(Address shippingAddress) { 
        this.shippingAddress = shippingAddress; 
    }
    
    public Address getBillingAddress() { return billingAddress; }
    public void setBillingAddress(Address billingAddress) { 
        this.billingAddress = billingAddress; 
    }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { 
        this.orderItems = orderItems; 
        calculateTotal();
    }
    
    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }
    
    // Business methods with relationships
    public double calculateTotal() {
        double itemsTotal = 0.0;
        for (OrderItem item : orderItems) {
            itemsTotal += item.getSubtotal();
        }
        this.totalAmount = itemsTotal;
        return totalAmount + shippingCost + taxAmount;
    }
    
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        System.out.println("Order " + orderId + " status updated to: " + newStatus);
    }
    
    public boolean cancelOrder() {
        if (status == OrderStatus.PENDING || status == OrderStatus.CONFIRMED) {
            this.status = OrderStatus.CANCELLED;
            System.out.println("Order " + orderId + " has been cancelled.");
            return true;
        }
        System.out.println("Cannot cancel order in status: " + status);
        return false;
    }
    
    public Invoice generateInvoice() {
        this.invoice = new Invoice();
        this.invoice.setOrderId(this.orderId);
        this.invoice.setCustomerId(this.customerId);
        this.invoice.setTotalAmount(calculateTotal());
        this.invoice.setInvoiceDate(new Date());
        System.out.println("Invoice generated for order: " + orderId);
        return this.invoice;
    }
    
    public void addOrderItem(OrderItem item) {
        if (item != null) {
            item.setOrderId(this.orderId);
            this.orderItems.add(item);
            calculateTotal();
        }
    }
    
    public boolean applyCoupon(String couponCode) {
        System.out.println("Coupon " + couponCode + " applied to order: " + orderId);
        return true;
    }
}