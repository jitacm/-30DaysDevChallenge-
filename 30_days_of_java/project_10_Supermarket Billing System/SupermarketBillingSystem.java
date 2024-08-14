import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10.2f %-10d %-10.2f", name, price, quantity, getTotalPrice());
    }
}

public class SupermarketBillingSystem {
    private static ArrayList<Item> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\nSupermarket Billing System");
            System.out.println("1. Add Item");
            System.out.println("2. View Cart");
            System.out.println("3. Generate Bill");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addItem() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();

        Item newItem = new Item(name, price, quantity);
        itemList.add(newItem);

        System.out.println("Item added to cart.");
    }

    private static void viewCart() {
        if (itemList.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            System.out.printf("%-20s %-10s %-10s %-10s\n", "Item Name", "Price", "Quantity", "Total");
            for (Item item : itemList) {
                System.out.println(item);
            }
        }
    }

    private static void generateBill() {
        if (itemList.isEmpty()) {
            System.out.println("Your cart is empty. Add items before generating a bill.");
            return;
        }

        double grandTotal = 0;
        System.out.println("\nBilling Details:");
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Item Name", "Price", "Quantity", "Total");
        for (Item item : itemList) {
            System.out.println(item);
            grandTotal += item.getTotalPrice();
        }

        System.out.printf("\nGrand Total: %.2f\n", grandTotal);
        itemList.clear();
        System.out.println("Thank you for shopping with us!");
    }
}