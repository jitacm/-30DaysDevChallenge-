import java.time.LocalDateTime;

public class AlarmTask {
    private String taskName;
    private LocalDateTime alarmTime;

    public AlarmTask(String taskName, LocalDateTime alarmTime) {
        this.taskName = taskName;
        this.alarmTime = alarmTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDateTime getAlarmTime() {
        return alarmTime;
    }

    @Override
    public String toString() {
        return "AlarmTask{" +
                "taskName='" + taskName + '\'' +
                ", alarmTime=" + alarmTime +
                '}';
    }
}

