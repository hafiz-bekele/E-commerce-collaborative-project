import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

public class TestProduct {
    public static void main(String[] args) {
        System.out.println("=== PRODUCT MANAGEMENT SYSTEM TEST ===\n");
        
        // Create service
        ProductService service = new ProductService();
        
        // Test 1: Display all products
        System.out.println("1. DISPLAY ALL PRODUCTS:");
        service.displayAllProducts();
        
        // Test 2: Search products
        System.out.println("\n2. SEARCH FOR 'lap':");
        for (Product p : service.searchProducts("lap")) {
            System.out.println("   Found: " + p.getName() + " - $" + p.getPrice());
        }
        
        // Test 3: Get product by ID
        System.out.println("\n3. GET PRODUCT BY ID (ID=2):");
        Product product = service.getProductById(2);
        if (product != null) {
            System.out.println("   Found: " + product.getName());
            System.out.println("   Available: " + product.isAvailable());
        }
        
        // Test 4: Add new product
        System.out.println("\n4. ADD NEW PRODUCT:");
        Product newProduct = new Product(5, "Tablet", 299.99, 15);
        service.addProduct(newProduct);
        
        // Test 5: Update stock
        System.out.println("\n5. UPDATE PRODUCT STOCK:");
        service.updateProductStock(1, 5);
        
        // Final display
        System.out.println("\n6. FINAL PRODUCT COUNT:");
        System.out.println("   Total products: " + service.getProductCount());
        
        System.out.println("\n✅ ALL TESTS COMPLETED SUCCESSFULLY!");
    }
}
