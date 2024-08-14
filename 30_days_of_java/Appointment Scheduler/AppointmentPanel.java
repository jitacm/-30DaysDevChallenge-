import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class AppointmentPanel extends JPanel {
    private DataModel dataModel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JComboBox<Client> clientComboBox;
    private JSpinner dateTimeSpinner;

    public AppointmentPanel(DataModel dataModel) {
        this.dataModel = dataModel;
        setLayout(new GridLayout(5, 2));

        titleField = new JTextField();
        descriptionArea = new JTextArea();
        clientComboBox = new JComboBox<>(dataModel.getClients().toArray(new Client[0]));
        dateTimeSpinner = new JSpinner(new SpinnerDateModel());
        dateTimeSpinner.setEditor(new JSpinner.DateEditor(dateTimeSpinner, "yyyy-MM-dd HH:mm"));

        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Date & Time:"));
        add(dateTimeSpinner);
        add(new JLabel("Client:"));
        add(clientComboBox);
        add(new JLabel("Description:"));
        add(new JScrollPane(descriptionArea));
        add(new JLabel(""));
        add(addButton);
    }

    private void addAppointment() {
        String title = titleField.getText();
        LocalDateTime dateTime = LocalDateTime.ofInstant(((SpinnerDateModel) dateTimeSpinner.getModel()).getDate().toInstant(), java.time.ZoneId.systemDefault());
        Client client = (Client) clientComboBox.getSelectedItem();
        String description = descriptionArea.getText();

        if (client == null) {
            JOptionPane.showMessageDialog(this, "Please select a client before adding an appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Appointment appointment = new Appointment(title, dateTime, client, description);
        dataModel.getAppointments().add(appointment);

        JOptionPane.showMessageDialog(this, "Appointment added successfully!");
    }
}
