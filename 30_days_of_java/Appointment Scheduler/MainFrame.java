import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private CalendarPanel calendarPanel;
    private AppointmentPanel appointmentPanel;
    private ReminderPanel reminderPanel;
    private PersistenceManager persistenceManager;

    public MainFrame() {
        setTitle("Appointment Scheduler");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load data
        persistenceManager = new PersistenceManager();
        DataModel dataModel = persistenceManager.loadData();

        calendarPanel = new CalendarPanel(dataModel);
        appointmentPanel = new AppointmentPanel(dataModel);
        reminderPanel = new ReminderPanel(dataModel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Calendar", calendarPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);
        tabbedPane.addTab("Reminders", reminderPanel);

        add(tabbedPane);

        // Save data on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                persistenceManager.saveData(dataModel);
            }
        });
    }
}
