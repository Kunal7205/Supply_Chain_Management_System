# Supply Chain Management System (API) 🚀

The Supply Chain Management System (SCMS) is a backend API developed using **Spring Boot**. It is designed to streamline supply chain operations, including order management, product inventory, and supplier coordination. This API efficiently manages customer orders, product data, and supplier information, enabling automated and scalable supply chain processes.

## ✨ Features

- **Customer Management:**  
  - CRUD operations for customer profiles.  
  - Retrieve customer order history and details.  

- **Order Management:**  
  - Handle order creation, updates, and tracking.  
  - Automatically update product inventory upon order placement.  
  - Support for bulk order processing.  

- **Product Management:**  
  - Manage product information linked to suppliers.  
  - Automatically add new products to the inventory if not present when ordered.  

- **Supplier Management:**  
  - Manage supplier profiles and track supplied products.  
  - Monitor product availability from various suppliers.  

## 🗂️ Entity Relationships

- **Customer to Order (One-to-Many):** Each customer can place multiple orders.  
- **Order to Product (One-to-Many):** An order can contain multiple products.  
- **Supplier to Product (One-to-Many):** Each supplier can supply multiple products.  

## 🛠️ Tech Stack

- **Backend:** Spring Boot  
- **Database:** SQL (JPA/Hibernate)  
- **IDE:** Eclipse  

## 💡 Key Benefits

- **Automation:** Automatically adds ordered products to the inventory if not already present.  
- **Efficiency:** Streamlines order processing and inventory management.  
- **Scalability:** Supports a growing number of customers, orders, and products.  
- **Data Integrity:** Maintains consistent and accurate data throughout the supply chain.  

---

Feel free to customize the README file further as per your project requirements. Let me know if you need help with anything else! 😊
