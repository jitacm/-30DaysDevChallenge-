#include <iostream>
#include <vector>
#include <string>

using namespace std;


class Product {
public:
    int id;
    string name;
    double price;
    int stock;

    Product(int id, string name, double price, int stock) 
        : id(id), name(name), price(price), stock(stock) {}

    void display() {
        cout << "ID: " << id << ", Name: " << name << ", Price: $" << price << ", Stock: " << stock << endl;
    }
};


class Customer {
public:
    int id;
    string name;
    string email;

    Customer(int id, string name, string email) 
        : id(id), name(name), email(email) {}

    void display() {
        cout << "ID: " << id << ", Name: " << name << ", Email: " << email << endl;
    }
};


class Order {
public:
    int orderId;
    int customerId;
    vector<Product> products;

    Order(int orderId, int customerId, vector<Product> products) 
        : orderId(orderId), customerId(customerId), products(products) {}

    void display() {
        cout << "Order ID: " << orderId << ", Customer ID: " << customerId << endl;
        for (auto &product : products) {
            product.display();
        }
    }
};


class OnlineRetailSystem {
public:
    vector<Product> products;
    vector<Customer> customers;
    vector<Order> orders;

    void addProduct(Product product) {
        products.push_back(product);
    }

    void addCustomer(Customer customer) {
        customers.push_back(customer);
    }

    void placeOrder(Order order) {
        orders.push_back(order);
    }

    void displayProducts() {
        cout << "Products:\n";
        for (auto &product : products) {
            product.display();
        }
    }

    void displayCustomers() {
        cout << "Customers:\n";
        for (auto &customer : customers) {
            customer.display();
        }
    }

    void displayOrders() {
        cout << "Orders:\n";
        for (auto &order : orders) {
            order.display();
        }
    }
};

int main() {
    OnlineRetailSystem system;

   
    system.addProduct(Product(1, "Laptop", 999.99, 10));
    system.addProduct(Product(2, "Smartphone", 499.99, 20));
    system.addProduct(Product(3, "Tablet", 299.99, 15));


    system.addCustomer(Customer(1, "Alice", "alice@example.com"));
    system.addCustomer(Customer(2, "Bob", "bob@example.com"));

   
    vector<Product> order1Products = { system.products[0], system.products[1] };
    system.placeOrder(Order(1, 1, order1Products));

    vector<Product> order2Products = { system.products[2] };
    system.placeOrder(Order(2, 2, order2Products));

    
    system.displayProducts();
    system.displayCustomers();
    system.displayOrders();

    return 0;
}