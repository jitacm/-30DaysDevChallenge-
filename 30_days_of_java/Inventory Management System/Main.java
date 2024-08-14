import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryManagementSystem().setVisible(true));
    }
}

class InventoryManagementSystem extends JFrame {
    private HashMap<String, Integer> inventory;
    private DefaultListModel<String> listModel;
    private JList<String> productList;
    private JTextArea transactionLog;

    private static final int LOW_STOCK_THRESHOLD = 10; // Define your low stock threshold here

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inventory = loadInventory();  // Load inventory from file
        listModel = new DefaultListModel<>();
        updateProductList();

        productList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(productList);

        transactionLog = new JTextArea(5, 30);
        transactionLog.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(transactionLog);

        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton updateButton = new JButton("Update Item");

        addButton.addActionListener(e -> addItem());
        removeButton.addActionListener(e -> removeItem());
        updateButton.addActionListener(e -> updateItem());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(logScrollPane, BorderLayout.EAST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveInventory();  // Save inventory to file when closing
            }
        });
    }

    private void updateProductList() {
        listModel.clear();
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String productInfo = entry.getKey() + " - Quantity: " + entry.getValue();
            if (entry.getValue() < LOW_STOCK_THRESHOLD) {
                productInfo += " (Low Stock)";
            }
            listModel.addElement(productInfo);
        }
    }

    private void addItem() {
        String productName = JOptionPane.showInputDialog(this, "Enter product name:");
        if (productName != null && !productName.isEmpty()) {
            String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity:");
            try {
                int quantity = Integer.parseInt(quantityStr);
                inventory.put(productName, inventory.getOrDefault(productName, 0) + quantity);
                updateProductList();
                recordTransaction("Added " + quantity + " units of " + productName);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid quantity!");
            }
        }
    }

    private void removeItem() {
        String selectedItem = productList.getSelectedValue();
        if (selectedItem != null) {
            String productName = selectedItem.split(" - ")[0];
            inventory.remove(productName);
            updateProductList();
            recordTransaction("Removed " + productName);
        } else {
            JOptionPane.showMessageDialog(this, "No item selected!");
        }
    }

    private void updateItem() {
        String selectedItem = productList.getSelectedValue();
        if (selectedItem != null) {
            String productName = selectedItem.split(" - ")[0];
            String quantityStr = JOptionPane.showInputDialog(this, "Enter new quantity:");
            try {
                int quantity = Integer.parseInt(quantityStr);
                inventory.put(productName, quantity);
                updateProductList();
                recordTransaction("Updated " + productName + " to " + quantity + " units");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid quantity!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No item selected!");
        }
    }

    private void recordTransaction(String message) {
        transactionLog.append(message + "\n");
    }

    @SuppressWarnings("unchecked")
    private HashMap<String, Integer> loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("inventory.ser"))) {
            return (HashMap<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();  // Return an empty inventory if file doesn't exist
        }
    }

    private void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inventory.ser"))) {
            oos.writeObject(inventory);  // Save the inventory map to a file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
