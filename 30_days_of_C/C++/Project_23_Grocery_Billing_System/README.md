# Grocery Store Billing System

This is a simple grocery store billing system implemented in C. The system allows you to add products, display available products, add products to a cart, display the cart, and checkout to get a receipt with the total amount.

## Features

- **Add Product**: Add new products with name and price.
- **Display Products**: View a list of all available products.
- **Add to Cart**: Add selected products to the cart with specified quantities.
- **Display Cart**: View the items currently in the cart.
- **Checkout**: Calculate the total bill and generate a receipt.

## How to Run

1. **Clone the Repository**

    ```sh
    git clone https://github.com/yourusername/grocery-store-billing-system.git
    cd grocery-store-billing-system
    ```

2. **Compile the Code**

    Open a terminal and navigate to the directory containing the source code. Run the following command to compile the code:

    ```sh
    gcc -o billing_system billing_system.c
    ```

3. **Run the Program**

    After compiling, run the executable:

    ```sh
    ./billing_system
    ```

4. **Use the System**

    Follow the on-screen instructions to use the grocery store billing system. You can add products, view products, add items to the cart, display the cart, and checkout.

## Code Overview

### Structures

- `Product`: Represents a product with a name and price.
- `CartItem`: Represents an item in the cart with the product and quantity.

### Functions

- `addProduct()`: Adds a new product to the list.
- `displayProducts()`: Displays all available products.
- `addToCart()`: Adds a selected product to the cart.
- `displayCart()`: Displays items in the cart.
- `checkout()`: Calculates the total amount and generates a receipt.

### Main Function

The `main()` function displays a menu with options to:
1. Add Product
2. Display Products
3. Add to Cart
4. Display Cart
5. Checkout
6. Exit

