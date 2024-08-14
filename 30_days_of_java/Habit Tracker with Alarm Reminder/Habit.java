import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Habit implements Serializable {
    private String name;
    private List<LocalDate> completionDates;
    private String reminderTime;

    public Habit(String name) {
        this.name = name;
        this.completionDates = new ArrayList<>();
    }

    public void complete() {
        LocalDate today = LocalDate.now();
        if (!completionDates.contains(today)) {
            completionDates.add(today);
        }
    }

    public String getStats() {
        int streak = calculateStreak();
        int totalDays = completionDates.size();
        return "Habit: " + name + "\nStreak: " + streak + " days\nTotal Completions: " + totalDays + "\nReminder: " + (reminderTime != null ? reminderTime : "Not set");
    }

    private int calculateStreak() {
        int streak = 0;
        LocalDate today = LocalDate.now();
        for (int i = completionDates.size() - 1; i >= 0; i--) {
            LocalDate date = completionDates.get(i);
            if (ChronoUnit.DAYS.between(date, today.minusDays(streak)) == 0) {
                streak++;
            } else {
                break;
            }
        }
        return streak;
    }

    public void setReminderTime(String time) {
        this.reminderTime = time;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + (reminderTime != null ? " (Reminder: " + reminderTime + ")" : "");
    }
}
