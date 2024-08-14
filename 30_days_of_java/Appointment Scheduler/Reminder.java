import java.io.Serializable;
import java.time.LocalDateTime;

public class Reminder implements Serializable {
    private LocalDateTime remindTime;
    private Appointment appointment;

    public Reminder(LocalDateTime remindTime, Appointment appointment) {
        this.remindTime = remindTime;
        this.appointment = appointment;
    }

    public LocalDateTime getRemindTime() {
        return remindTime;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
