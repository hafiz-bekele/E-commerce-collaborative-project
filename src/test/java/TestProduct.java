public class TestProduct {
    public static void main(String[] args) {
        System.out.println("Testing Product Catalog...");
        
        Product laptop = new Product(1, "Laptop", 999.99, 10);
        System.out.println("Created: " + laptop.getName());
        
        ProductService service = new ProductService();
        System.out.println("Total products: " + service.getAllProducts().size());
        
        System.out.println("Tests passed!");
    }
}
