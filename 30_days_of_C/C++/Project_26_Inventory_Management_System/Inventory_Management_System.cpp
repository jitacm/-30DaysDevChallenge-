#include <iostream>
#include <vector>
#include <string>

class Item {
public:
    Item(std::string name, int quantity, double price) 
        : name(name), quantity(quantity), price(price) {}

    std::string getName() const { return name; }
    int getQuantity() const { return quantity; }
    double getPrice() const { return price; }

    void setQuantity(int quantity) { this->quantity = quantity; }
    void setPrice(double price) { this->price = price; }

private:
    std::string name;
    int quantity;
    double price;
};

class Inventory {
public:
    void addItem(const Item& item) {
        items.push_back(item);
    }

    void viewItems() const {
        for (const auto& item : items) {
            std::cout << "Name: " << item.getName() 
                      << ", Quantity: " << item.getQuantity() 
                      << ", Price: $" << item.getPrice() << std::endl;
        }
    }

    void removeItem(const std::string& name) {
        for (auto it = items.begin(); it != items.end(); ++it) {
            if (it->getName() == name) {
                items.erase(it);
                std::cout << "Item removed successfully.\n";
                return;
            }
        }
        std::cout << "Item not found.\n";
    }

private:
    std::vector<Item> items;
};

int main() {
    Inventory inventory;

    while (true) {
        std::cout << "\nInventory Management System\n";
        std::cout << "1. Add Item\n";
        std::cout << "2. View Items\n";
        std::cout << "3. Remove Item\n";
        std::cout << "4. Exit\n";
        std::cout << "Enter your choice: ";
        
        int choice;
        std::cin >> choice;

        if (choice == 1) {
            std::string name;
            int quantity;
            double price;

            std::cout << "Enter item name: ";
            std::cin >> name;
            std::cout << "Enter item quantity: ";
            std::cin >> quantity;
            std::cout << "Enter item price: ";
            std::cin >> price;

            inventory.addItem(Item(name, quantity, price));
        } else if (choice == 2) {
            inventory.viewItems();
        } else if (choice == 3) {
            std::string name;
            std::cout << "Enter item name to remove: ";
            std::cin >> name;

            inventory.removeItem(name);
        } else if (choice == 4) {
            break;
        } else {
            std::cout << "Invalid choice. Please try again.\n";
        }
    }

    return 0;
}