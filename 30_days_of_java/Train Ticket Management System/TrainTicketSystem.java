import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainTicketSystem {
    private static final double PRICE_PER_TICKET = 50.0; // Example price per ticket
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Train Ticket Management System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        frame.add(nameLabel);
        
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 30, 150, 25);
        frame.add(nameField);
        
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(30, 70, 100, 25);
        frame.add(destinationLabel);
        
        String[] destinations = {"New York", "Los Angeles", "Chicago", "Houston"};
        JComboBox<String> destinationComboBox = new JComboBox<>(destinations);
        destinationComboBox.setBounds(150, 70, 150, 25);
        frame.add(destinationComboBox);
        
        JLabel ticketsLabel = new JLabel("Number of Tickets:");
        ticketsLabel.setBounds(30, 110, 150, 25);
        frame.add(ticketsLabel);
        
        JTextField ticketsField = new JTextField();
        ticketsField.setBounds(150, 110, 150, 25);
        frame.add(ticketsField);
        
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(30, 150, 120, 25);
        frame.add(calculateButton);
        
        JLabel costLabel = new JLabel("Total Cost: $0.00");
        costLabel.setBounds(30, 190, 150, 25);
        frame.add(costLabel);
        
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setBounds(160, 150, 120, 25);
        frame.add(purchaseButton);
        
        JTextArea receiptArea = new JTextArea();
        receiptArea.setBounds(30, 230, 320, 100); // Increased height
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(true);
        receiptArea.setWrapStyleWord(true);
        frame.add(receiptArea);
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfTickets = Integer.parseInt(ticketsField.getText());
                    double totalCost = numberOfTickets * PRICE_PER_TICKET;
                    costLabel.setText(String.format("Total Cost: $%.2f", totalCost));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number of tickets.");
                }
            }
        });
        
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    if (name.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter your name.");
                        return;
                    }
                    
                    int numberOfTickets = Integer.parseInt(ticketsField.getText());
                    String destination = (String) destinationComboBox.getSelectedItem();
                    double totalCost = numberOfTickets * PRICE_PER_TICKET;
                    
                    String receipt = "Receipt:\n" +
                                     "Name: " + name + "\n" +
                                     "Destination: " + destination + "\n" +
                                     "Number of Tickets: " + numberOfTickets + "\n" +
                                     "Total Cost: $" + totalCost;
                    receiptArea.setText(receipt);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please calculate the cost before purchasing.");
                }
            }
        });
        
        frame.setVisible(true);
    }
}
