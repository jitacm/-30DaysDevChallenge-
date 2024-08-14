import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private String title;
    private LocalDateTime dateTime;
    private Client client;
    private String description;

    public Appointment(String title, LocalDateTime dateTime, Client client, String description) {
        this.title = title;
        this.dateTime = dateTime;
        this.client = client;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Client getClient() {
        return client;
    }

    public String getDescription() {
        return description;
    }

    // Add setters if needed
}
