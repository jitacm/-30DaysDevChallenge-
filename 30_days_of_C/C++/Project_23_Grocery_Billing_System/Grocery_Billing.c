#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_PRODUCTS 100
#define MAX_NAME_LENGTH 50

typedef struct {
    char name[MAX_NAME_LENGTH];
    float price;
} Product;

typedef struct {
    Product product;
    int quantity;
} CartItem;

Product products[MAX_PRODUCTS];
int productCount = 0;
CartItem cart[MAX_PRODUCTS];
int cartCount = 0;

void addProduct() {
    if (productCount >= MAX_PRODUCTS) {
        printf("Cannot add more products.\n");
        return;
    }

    Product newProduct;
    printf("Enter product name: ");
    scanf("%s", newProduct.name);
    printf("Enter product price: ");
    scanf("%f", &newProduct.price);

    products[productCount] = newProduct;
    productCount++;
    printf("Product added successfully.\n");
}

void displayProducts() {
    if (productCount == 0) {
        printf("No products available.\n");
        return;
    }

    printf("Available Products:\n");
    for (int i = 0; i < productCount; i++) {
        printf("%d. %s - $%.2f\n", i + 1, products[i].name, products[i].price);
    }
}

void addToCart() {
    if (productCount == 0) {
        printf("No products available to add to cart.\n");
        return;
    }

    int productIndex, quantity;
    displayProducts();
    printf("Enter product number to add to cart: ");
    scanf("%d", &productIndex);
    productIndex--; // Adjusting to array index

    if (productIndex < 0 || productIndex >= productCount) {
        printf("Invalid product number.\n");
        return;
    }

    printf("Enter quantity: ");
    scanf("%d", &quantity);

    CartItem newItem;
    newItem.product = products[productIndex];
    newItem.quantity = quantity;

    cart[cartCount] = newItem;
    cartCount++;
    printf("Product added to cart.\n");
}

void displayCart() {
    if (cartCount == 0) {
        printf("Cart is empty.\n");
        return;
    }

    printf("Cart Items:\n");
    for (int i = 0; i < cartCount; i++) {
        printf("%d. %s - $%.2f x %d\n", i + 1, cart[i].product.name, cart[i].product.price, cart[i].quantity);
    }
}

void checkout() {
    if (cartCount == 0) {
        printf("Cart is empty.\n");
        return;
    }

    float total = 0.0;
    printf("Receipt:\n");
    for (int i = 0; i < cartCount; i++) {
        float itemTotal = cart[i].product.price * cart[i].quantity;
        printf("%d. %s - $%.2f x %d = $%.2f\n", i + 1, cart[i].product.name, cart[i].product.price, cart[i].quantity, itemTotal);
        total += itemTotal;
    }

    printf("Total Amount: $%.2f\n", total);
    cartCount = 0; // Clear cart after checkout
}

int main() {
    int choice;
    while (1) {
        printf("\nGrocery Store Billing System\n");
        printf("1. Add Product\n");
        printf("2. Display Products\n");
        printf("3. Add to Cart\n");
        printf("4. Display Cart\n");
        printf("5. Checkout\n");
        printf("6. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                displayProducts();
                break;
            case 3:
                addToCart();
                break;
            case 4:
                displayCart();
                break;
            case 5:
                checkout();
                break;
            case 6:
                printf("Exiting the program. Goodbye!\n");
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
