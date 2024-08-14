import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class ReminderPanel extends JPanel {
    private DataModel dataModel;

    public ReminderPanel(DataModel dataModel) {
        this.dataModel = dataModel;
        setLayout(new GridLayout(4, 2));

        JComboBox<Appointment> appointmentComboBox = new JComboBox<>(dataModel.getAppointments().toArray(new Appointment[0]));
        JSpinner reminderTimeSpinner = new JSpinner(new SpinnerDateModel());

        JButton setReminderButton = new JButton("Set Reminder");
        setReminderButton.addActionListener(e -> setReminder(appointmentComboBox, reminderTimeSpinner));

        add(new JLabel("Appointment:"));
        add(appointmentComboBox);
        add(new JLabel("Reminder Time:"));
        add(reminderTimeSpinner);
        add(new JLabel(""));
        add(setReminderButton);
    }

    private void setReminder(JComboBox<Appointment> appointmentComboBox, JSpinner reminderTimeSpinner) {
        Appointment appointment = (Appointment) appointmentComboBox.getSelectedItem();
        LocalDateTime remindTime = LocalDateTime.now(); // For simplicity
        Reminder reminder = new Reminder(remindTime, appointment);
        dataModel.getReminders().add(reminder);

        JOptionPane.showMessageDialog(this, "Reminder set successfully!");
    }
}
