import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataModel implements Serializable {
    private List<Appointment> appointments;
    private List<Client> clients;
    private List<Reminder> reminders;

    public DataModel() {
        appointments = new ArrayList<>();
        clients = new ArrayList<>();
        reminders = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    // Add methods to manage appointments, clients, and reminders
}
