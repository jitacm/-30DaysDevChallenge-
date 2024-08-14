import java.io.Serializable;
import java.util.Date;

public class MaintenanceTask implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String description;
    private Date date;
    private boolean completed;

    public MaintenanceTask(String description, Date date) {
        this.description = description;
        this.date = date;
        this.completed = false;
    }

    // Getters and setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
