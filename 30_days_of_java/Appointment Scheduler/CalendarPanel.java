import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class CalendarPanel extends JPanel {
    @SuppressWarnings("unused")
    private DataModel dataModel;

    public CalendarPanel(DataModel dataModel) {
        this.dataModel = dataModel;
        setLayout(new BorderLayout());

        // A JTable to show appointments with their details
        String[] columnNames = {"Title", "Date & Time", "Client", "Description"};
        Object[][] data = new Object[dataModel.getAppointments().size()][4];

        for (int i = 0; i < dataModel.getAppointments().size(); i++) {
            Appointment appointment = dataModel.getAppointments().get(i);
            data[i][0] = appointment.getTitle();
            data[i][1] = appointment.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            data[i][2] = appointment.getClient() != null ? appointment.getClient().getName() : "No Client";
            data[i][3] = appointment.getDescription();
        }

        JTable appointmentTable = new JTable(data, columnNames);
        add(new JScrollPane(appointmentTable), BorderLayout.CENTER);
    }
}
