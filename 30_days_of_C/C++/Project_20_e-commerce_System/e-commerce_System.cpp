#include <iostream>
#include <vector>
#include <string>

using namespace std;


class Product {
public:
    int id;
    string name;
    double price;

    Product(int id, string name, double price) : id(id), name(name), price(price) {}
};


class Customer {
public:
    int id;
    string name;

    Customer(int id, string name) : id(id), name(name) {}
};


class Order {
public:
    int id;
    Customer customer;
    vector<Product> products;

    Order(int id, Customer customer) : id(id), customer(customer) {}

    void addProduct(Product product) {
        products.push_back(product);
    }

    double getTotal() {
        double total = 0.0;
        for (auto &product : products) {
            total += product.price;
        }
        return total;
    }

    void printOrder() {
        cout << "Order ID: " << id << "\nCustomer: " << customer.name << "\nProducts:\n";
        for (auto &product : products) {
            cout << "  - " << product.name << ": $" << product.price << "\n";
        }
        cout << "Total: $" << getTotal() << "\n";
    }
};


class Store {
public:
    vector<Product> products;
    vector<Order> orders;
    int nextProductId;
    int nextOrderId;

    Store() : nextProductId(1), nextOrderId(1) {}

    void addProduct(string name, double price) {
        products.emplace_back(nextProductId++, name, price);
    }

    void listProducts() {
        cout << "Products available:\n";
        for (auto &product : products) {
            cout << product.id << ". " << product.name << ": $" << product.price << "\n";
        }
    }

    void createOrder(Customer customer) {
        Order order(nextOrderId++, customer);
        orders.push_back(order);
    }

    Order* getOrder(int orderId) {
        for (auto &order : orders) {
            if (order.id == orderId) {
                return &order;
            }
        }
        return nullptr;
    }

    void addProductToOrder(int orderId, int productId) {
        Order* order = getOrder(orderId);
        if (order) {
            for (auto &product : products) {
                if (product.id == productId) {
                    order->addProduct(product);
                    break;
                }
            }
        }
    }
};


int main() {
    Store store;
    store.addProduct("Laptop", 999.99);
    store.addProduct("Smartphone", 499.99);
    store.addProduct("Tablet", 299.99);

    Customer customer(1, "John Doe");
    store.createOrder(customer);
    store.listProducts();

    int orderId = 1;
    store.addProductToOrder(orderId, 1);
    store.addProductToOrder(orderId, 2);

    Order* order = store.getOrder(orderId);
    if (order) {
        order->printOrder();
    } else {
        cout << "Order not found.\n";
    }

    return 0;
}