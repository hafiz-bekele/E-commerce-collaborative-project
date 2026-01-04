

* Docs™ to Markdown version 2.0β1
* Sun Jan 04 2026 06:42:34 GMT-0800 (PST)
* Source doc: phase 6
* Tables are currently converted to HTML tables.
----->



# **PHASE 6: UNIT TESTING WITH JUNIT**

**Project Title:** *E-Commerce Web Application*


---


## **🔷 1. Introduction to Unit Testing**

Unit Testing is a software testing technique where **individual units or components** of a system are tested independently to ensure they work correctly. \
In this project, **JUnit** is used as the unit testing framework to test the core functionalities of the E-Commerce Web Application.

JUnit helps ensure:



* Code correctness
* Early bug detection
* Easier maintenance
* Reliable system behavior


---


## **🔷 2. Objectives of Unit Testing**

The main objectives of Phase 6 are:



* To verify that each method works as expected
* To automate testing of system components
* To validate business logic using assertions
* To support Test-Driven Development (TDD)
* To improve code quality and reliability


---


## **🔷 3. Tools and Technologies Used**


<table>
  <tr>
   <td><strong>Tool</strong>
   </td>
   <td><strong>Purpose</strong>
   </td>
  </tr>
  <tr>
   <td>Java
   </td>
   <td>Programming Language
   </td>
  </tr>
  <tr>
   <td>JUnit 5
   </td>
   <td>Unit Testing Framework
   </td>
  </tr>
  <tr>
   <td>IntelliJ / Eclipse
   </td>
   <td>IDE
   </td>
  </tr>
  <tr>
   <td>GitHub
   </td>
   <td>Version Control
   </td>
  </tr>
</table>



---


## **🔷 4. Key Features of JUnit Used**


### **✅ Automated Test Execution**

JUnit automatically runs test cases and reports pass/fail results without manual checking.


### **✅ Readable and Structured Test Cases**

JUnit uses annotations such as:



* `@Test`
* `@BeforeEach`
* `@AfterEach`


### **✅ Assertions for Validation**

Assertions are used to compare expected and actual results.


### **✅ Support for Test-Driven Development (TDD)**

JUnit allows writing test cases before actual implementation.


---


## **🔷 5. Test Case Design Strategy**

Each important class in the system is tested individually. \
The following classes are selected for unit testing:



* User
* Product
* Cart
* Order
* Payment


---


## **🔷 6. Sample JUnit Test Cases**


### **6.1 User Class Test**

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test

    void testUserLoginSuccess() {

        User user = new User("test@mail.com", "1234");

        boolean result = user.login("test@mail.com", "1234");

        assertTrue(result);

    }

    @Test

    void testUserLoginFailure() {

        User user = new User("test@mail.com", "1234");

        boolean result = user.login("test@mail.com", "wrong");

        assertFalse(result);

    }

}

📌 **Explanation: \
**These tests verify successful and unsuccessful login scenarios.


---


### **6.2 Product Class Test**

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test

    void testProductStock() {

        Product product = new Product("Laptop", 10);

        assertEquals(10, product.getStock());

    }

    @Test

    void testUpdateStock() {

        Product product = new Product("Laptop", 10);

        product.updateStock(5);

        assertEquals(5, product.getStock());

    }

}

📌 **Explanation: \
**These tests ensure product stock values are correct.


---


### **6.3 Cart Class Test**

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CartTest {

    @Test

    void testAddToCart() {

        Cart cart = new Cart();

        cart.addItem("Phone");

        assertEquals(1, cart.getItemCount());

    }

    @Test

    void testRemoveFromCart() {

        Cart cart = new Cart();

        cart.addItem("Phone");

        cart.removeItem("Phone");

        assertEquals(0, cart.getItemCount());

    }

}

📌 **Explanation: \
**Tests adding and removing items from the cart.


---


### **6.4 Order Class Test**

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test

    void testCreateOrder() {

        Order order = new Order();

        boolean result = order.createOrder();

        assertTrue(result);

    }

}

📌 **Explanation: \
**Checks whether order creation works correctly.


---


### **6.5 Payment Class Test**

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test

    void testPaymentSuccess() {

        Payment payment = new Payment();

        boolean result = payment.processPayment(500);

        assertTrue(result);

    }

    @Test

    void testPaymentFailure() {

        Payment payment = new Payment();

        boolean result = payment.processPayment(-100);

        assertFalse(result);

    }

}

📌 **Explanation: \
**Validates successful and failed payment cases.


---


## **🔷 7. Assertions Used in Testing**


<table>
  <tr>
   <td><strong>Assertion</strong>
   </td>
   <td><strong>Purpose</strong>
   </td>
  </tr>
  <tr>
   <td>assertEquals
   </td>
   <td>Compare expected and actual
   </td>
  </tr>
  <tr>
   <td>assertTrue
   </td>
   <td>Check true condition
   </td>
  </tr>
  <tr>
   <td>assertFalse
   </td>
   <td>Check false condition
   </td>
  </tr>
  <tr>
   <td>assertNotNull
   </td>
   <td>Ensure object is created
   </td>
  </tr>
</table>



---


## **🔷 8. Test Execution and Results**



* All test cases were executed using JUnit
* Tests passed successfully without errors
* Failures were identified early and fixed
* Automated testing reduced debugging time

📌 **Test Results Status:** ✅ PASSED


---


## **🔷 9. Benefits of Unit Testing in This Project**



* Ensures correctness of each module
* Simplifies debugging
* Improves maintainability
* Prevents regression errors
* Increases confidence in code quality


---


## **🔷 10. Conclusion**

Phase 6 successfully implemented **Unit Testing using JUnit** for the E-Commerce Web Application. \
All core functionalities were tested using **automated, readable, and structured test cases**. \
JUnit assertions validated system behavior and ensured reliable software quality.


---

