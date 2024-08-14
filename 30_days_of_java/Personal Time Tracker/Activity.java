import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Duration;

public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Activity(String name) {
        this.name = name;
    }

    public void start() {
        startTime = LocalDateTime.now();
    }

    public void stop() {
        endTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        if (startTime != null && endTime != null) {
            return Duration.between(startTime, endTime);
        }
        return Duration.ZERO;
    }

    public String getFormattedDuration() {
        Duration duration = getDuration();
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds() % 60;
        return String.format("%d minutes, %d seconds", minutes, seconds);
    }
}
