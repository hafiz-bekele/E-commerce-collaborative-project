
## **🔷 A. HIGH-LEVEL DESIGN (HLD)**


### **1. Overall System Architecture**

The E-Commerce Web Application is designed using a **3-Tier Architecture**. \
This architecture separates the system into three main layers to improve scalability, maintainability, and security.

**Layers Description:**



* **Presentation Layer:** User interface accessed via web browser
* **Application Layer:** Handles business logic and processing
* **Data Layer:** Stores and manages system data


### **System Architecture Diagram**

+-------------------+

|   User / Client   |

| (Web Browser UI)  |

+---------+---------+

          |

          | HTTP / HTTPS

          v

+-------------------+

|  Web Application  |

| (Frontend Layer)  |

+---------+---------+

          |

          | API Requests

          v

+-------------------+

| Application Server|

| (Business Logic)  |

| Node.js / Express |

+---------+---------+

          |

          | SQL Queries

          v

+-------------------+

|     Database      |

| MySQL / PostgreSQL|

+-------------------+

📌 **Architecture**

---



### **2. Module Diagram (Subsystems & Interfaces)**

The system is divided into different functional modules. Each module is responsible for a specific task and communicates with other modules through defined interfaces.

**Modules Description:**



* User Management
* Product Management
* Cart Management
* Order Management
* Payment Module
* Admin Module


### **Module Diagram**

+----------------------+

|   User Management    |

+----------+-----------+

           |

           v

+----------------------+

|   Cart Management    |

+----------+-----------+

           |

           v

+----------------------+

|   Order Management   |

+----------+-----------+

           |

           v

+----------------------+

|   Payment Module     |

+----------------------+

+----------------------+

| Product Management   |

+----------------------+

+----------------------+

|    Admin Module      |

+----------------------+

📌 Modules communicate using service calls and APIs.


---


### **3. Data Flow Diagram (DFD – Level 1)**

The Data Flow Diagram shows how data moves through the system from user interaction to database storage.


### **Data Flow Diagram**

[User]

   |

   | Login / Register Data

   v

[Authentication Process]

   |

   | Valid User

   v

[Browse Products]

   |

   | Selected Products

   v

[Cart System]

   |

   | Order Details

   v

[Order Processing]

   |

   | Payment Info

   v

[Payment Gateway]

   |

   | Confirmation

   v

[Database]

📌 **Main Data Stores:**



* User Data
* Product Data
* Order Data
* Payment Data


---


### **4. High-Level Database Schema**

The database is designed to store users, products, orders, and payments efficiently.


### **Database Tables**

USERS

-----------------

user_id (PK)

name

email

password

role

PRODUCTS

-----------------

product_id (PK)

name

price

stock

category_id (FK)

CATEGORIES

-----------------

category_id (PK)

category_name

ORDERS

-----------------

order_id (PK)

user_id (FK)

order_date

status

ORDER_ITEMS

-----------------

order_item_id (PK)

order_id (FK)

product_id (FK)

quantity

PAYMENTS

-----------------

payment_id (PK)

order_id (FK)

payment_method

payment_status

📌 **Relationships:**



* USERS 1 ——  ORDERS
* ORDERS 1 ——  ORDER_ITEMS
* PRODUCTS 1 ——  ORDER_ITEMS



## 4. High-Level Database Schema
```sql
-- USERS Table with constraints
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('customer', 'admin') DEFAULT 'customer',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email (email)
) ENGINE=InnoDB;

-- ORDERS Table with foreign keys
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL CHECK (total_amount >= 0),
    status ENUM('pending', 'processing', 'shipped', 'delivered') DEFAULT 'pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX idx_user_status (user_id, status)
);

-- ORDER_ITEMS linking table
CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    UNIQUE KEY unique_order_product (order_id, product_id)
);
```
---


## **🔷 B. LOW-LEVEL DESIGN (LLD)**

Low-Level Design focuses on the internal logic of each component, including classes, methods, algorithms, and validations.


---


### **1. Detailed Class Diagram (Method Definitions)**

     User        

| userId : int      |

| name : String     |

| email : String    |

| password : String |

| register()        |

| login()           |

| logout()          |

+-------------------+

|      Product      |

+-------------------+

| productId : int   |

| name : String     |

| price : double    |

| stock : int       | \
 \
 \
 \


+-------------------+

| addProduct()      |

| updateProduct()   |

| deleteProduct()   |

+-------------------+

 \
 \


+-------------------+

|       Cart        |

+-------------------+

| cartId : int      |

| userId : int      |

+-------------------+

| addToCart()       |

| removeFromCart()  |

| viewCart()        |

+-------------------+


---


### **2. Pseudocode / Algorithms**


#### **User Login Algorithm**

START

Input email and password

IF user exists AND password matches

    Allow login

ELSE

    Display error message

END


---


#### **Add to Cart Algorithm**

START

Select product

IF product is available

    Add product to cart

ELSE

    Show out of stock message

END


---


### **3. Sequence Diagram – Place Order**

The sequence diagram shows interactions between objects during order placement.

User -> UI : Select Product

UI -> Cart : Add Item

Cart -> Order : Create Order

Order -> Payment : Process Payment

Payment -> Database : Save Payment

Database -> Order : Confirmation

Order -> UI : Order Success

UI -> User : Show Confirmation


---


### **4. Error Handling & Validations**

IF login fields are empty

   Display validation error

IF password is incorrect

   Deny access

IF product stock is zero

   Disable checkout option

IF payment fails

   Cancel order

   Show retry message


---


## **✅ PHASE 5 COMPLETION SUMMARY**



* Designed complete **High-Level Architecture**
* Defined system **modules and data flow**
* Created **database schema**
* Detailed **classes, methods, algorithms**
* Explained **sequence flow and error handling**


---

