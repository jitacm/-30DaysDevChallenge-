import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ContactBookGUI extends JFrame {
    private ContactBook contactBook;
    private JList<Contact> contactList;
    private DefaultListModel<Contact> listModel;
    private JTextField nameField, phoneField, emailField, categoryField, searchField;

    public ContactBookGUI() {
        contactBook = ContactBook.loadFromFile("contacts.dat");
        setTitle("Contact Book");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                contactBook.saveToFile("contacts.dat");
            }
        });
    }

    private void initComponents() {
        // Left panel: contact list
        listModel = new DefaultListModel<>();
        for (Contact contact : contactBook.getContacts()) {
            listModel.addElement(contact);
        }
        contactList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(contactList);
        add(scrollPane, BorderLayout.WEST);

        // Right panel: contact details and buttons
        JPanel rightPanel = new JPanel(new BorderLayout());
        
        // Contact details
        JPanel detailsPanel = new JPanel(new GridLayout(5, 2));
        detailsPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        detailsPanel.add(nameField);
        detailsPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        detailsPanel.add(phoneField);
        detailsPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        detailsPanel.add(emailField);
        detailsPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        detailsPanel.add(categoryField);
        detailsPanel.add(new JLabel("Search:"));
        searchField = new JTextField();
        detailsPanel.add(searchField);
        rightPanel.add(detailsPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.CENTER);

        // Add action listeners
        addButton.addActionListener(e -> addContact());
        editButton.addActionListener(e -> editContact());
        deleteButton.addActionListener(e -> deleteContact());
        searchButton.addActionListener(e -> searchContacts());

        contactList.addListSelectionListener(e -> displayContactDetails());
    }

    private void addContact() {
        String phone = phoneField.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Contact newContact = new Contact(
            nameField.getText().trim(),
            phone,
            emailField.getText().trim(),
            categoryField.getText().trim()
        );
        if (contactBook.addContact(newContact)) {
            listModel.addElement(newContact);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "A contact with this phone number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            String phone = phoneField.getText().trim();
            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phone number is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Contact editedContact = new Contact(
                nameField.getText().trim(),
                phone,
                emailField.getText().trim(),
                categoryField.getText().trim()
            );
            if (contactBook.editContact(selectedIndex, editedContact)) {
                listModel.set(selectedIndex, editedContact);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "A contact with this phone number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            contactBook.deleteContact(selectedIndex);
            listModel.remove(selectedIndex);
            clearFields();
        }
    }

    private void searchContacts() {
        String query = searchField.getText().trim();
        listModel.clear();
        if (query.isEmpty()) {
            for (Contact contact : contactBook.getContacts()) {
                listModel.addElement(contact);
            }
        } else {
            for (Contact contact : contactBook.searchContacts(query)) {
                listModel.addElement(contact);
            }
        }
    }

    private void displayContactDetails() {
        Contact selectedContact = contactList.getSelectedValue();
        if (selectedContact != null) {
            nameField.setText(selectedContact.getName());
            phoneField.setText(selectedContact.getPhone());
            emailField.setText(selectedContact.getEmail());
            categoryField.setText(selectedContact.getCategory());
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        categoryField.setText("");
    }
}