# Inventory Management System

![Screenshot 2024-08-13 014622](https://github.com/user-attachments/assets/e617db32-37f7-4228-b35c-6bfd887be9e5)

## Objectives

- **Track Products and Quantities:** Maintain an updated list of all products and their quantities.
- **Manage Inventory Items:** Add, remove, and update items in the inventory.
- **Low Stock Alerts:** Automatically generate alerts for items with quantities below a defined threshold.
- **Record Transactions:** Track sales and restocking activities.
- **Data Persistence:** Store inventory data persistently using serialization for use across multiple sessions.

## Features

- **User-Friendly Interface:** Intuitive GUI to manage inventory operations.
- **Real-time Updates:** Immediately reflects changes to inventory items.
- **Low Stock Warning:** Highlights items with low stock levels for easy identification.
- **Persistent Data Storage:** Saves inventory data automatically when the application is closed.
- **Transaction Logging:** Logs each transaction for easy tracking of inventory changes.

## Technology Stack

- **Programming Language:** Java
- **GUI:** Swing
- **Data Storage:** Serialization

## Installation and Usage

1. **Clone the Repository:**
    ```sh
    git clone <repository-url>
    ```
2. **Navigate to the Project Directory:**
    ```sh
    cd inventory-management-system
    ```
3. **Compile the Java files:**
    ```sh
    javac Main.java
    ```
4. **Run the Application:**
    ```sh
    java Main
    ```

## Usage

- **Add Item:** Click "Add Item" to input a product name and quantity.
- **Remove Item:** Select an item from the list and click "Remove Item" to delete it from the inventory.
- **Update Item:** Select an item from the list and click "Update Item" to change its quantity.
- **Transaction Log:** Review all inventory changes in the transaction log panel.
