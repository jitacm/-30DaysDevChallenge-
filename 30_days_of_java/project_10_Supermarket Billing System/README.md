# Supermarket Billing System

## Project Overview
The **Supermarket Billing System** is a console-based Java application designed to manage supermarket billing operations. This application allows users to add items to a cart, view the cart, and generate a bill. It's a simple yet powerful tool for understanding basic Java concepts and applying them in a real-world scenario.

## Features
- **Add Item**: Add items to the cart by specifying the name, price, and quantity.
- **View Cart**: Display all items currently in the cart, including their total cost.
- **Generate Bill**: Calculate the total price of all items, display the bill, and clear the cart.
- **Exit**: Exit the application.

## Installation and Setup

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A text editor or an IDE like IntelliJ IDEA, Eclipse, or VS Code

### Steps to Run the Project
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/nikharepriyant/30DaysDevChallenge.git
    ```
   
2. **Navigate to the Project Folder**:
    ```bash
    cd 30DaysDevChallenge/30_days_of_java/SupermarketBillingSystem
    ```

3. **Compile the Java File**:
    ```bash
    javac SupermarketBillingSystem.java
    ```

4. **Run the Application**:
    ```bash
    java SupermarketBillingSystem
    ```

## Usage
Once the application is running, you will be presented with a menu offering the following options:

1. **Add Item**: Enter the item's name, price, and quantity to add it to your cart.
2. **View Cart**: Displays the items currently in your cart along with their price, quantity, and total cost.
3. **Generate Bill**: Displays a detailed bill with the total cost of all items and clears the cart for the next customer.
4. **Exit**: Closes the application.

### Example
```plaintext
Supermarket Billing System
1. Add Item
2. View Cart
3. Generate Bill
4. Exit
Choose an option: 1
Enter item name: Apple
Enter item price: 60
Enter item quantity: 6
Item added to cart.
```

## Code Structure
- **Item Class**: Represents an individual item with its name, price, and quantity. It also calculates the total price for the item.
- **SupermarketBillingSystem Class**: The main class containing the menu-driven interface. It manages the cart, displays the cart contents, generates the bill, and handles user input.

## Future Enhancements
- **Discount Management**: Add functionality to apply discounts based on total bill amount or specific items.
- **Search Functionality**: Implement a search feature to find items quickly in the cart.
- **Persistent Storage**: Save and load cart items from a file or database for more advanced use cases.

## Contribution
Feel free to fork this repository and submit pull requests if you have any ideas for improving this project. All contributions are welcome!

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For any inquiries or feedback, please contact me at [nikharepriyant@gmail.com](mailto:nikharepriyant@gmail.com).
