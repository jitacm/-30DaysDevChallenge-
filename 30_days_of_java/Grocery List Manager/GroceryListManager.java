import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GroceryListManager extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> groceryList;
    private JTextField itemField;
    private JButton addButton, removeButton, saveButton, loadButton;

    public GroceryListManager() {
        setTitle("Grocery List Manager");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        groceryList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(groceryList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        itemField = new JTextField(20);
        addButton = new JButton("Add");
        inputPanel.add(itemField);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        removeButton = new JButton("Remove");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = itemField.getText().trim();
                if (!item.isEmpty()) {
                    listModel.addElement(item);
                    itemField.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = groceryList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveList();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadList();
            }
        });
    }

    private void saveList() {
        try (PrintWriter out = new PrintWriter(new FileWriter("grocery_list.txt"))) {
            for (int i = 0; i < listModel.size(); i++) {
                out.println(listModel.get(i));
            }
            JOptionPane.showMessageDialog(this, "List saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving list: " + e.getMessage());
        }
    }

    private void loadList() {
        try (BufferedReader in = new BufferedReader(new FileReader("grocery_list.txt"))) {
            listModel.clear();
            String item;
            while ((item = in.readLine()) != null) {
                listModel.addElement(item);
            }
            JOptionPane.showMessageDialog(this, "List loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading list: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GroceryListManager().setVisible(true);
            }
        });
    }
}
