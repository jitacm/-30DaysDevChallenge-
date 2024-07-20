import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

public class AlarmManager {
    private List<AlarmTask> tasks;
    private Timer timer;

    public AlarmManager() {
        tasks = new ArrayList<>();
        timer = new Timer();
    }

    public void addTask(AlarmTask task) {
        tasks.add(task);
        scheduleTask(task);
    }

    private void scheduleTask(AlarmTask task) {
        long delay = calculateDelay(task.getAlarmTime());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                triggerAlarm(task);
            }
        }, delay);
    }

    private long calculateDelay(LocalDateTime alarmTime) {
        return alarmTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis();
    }

    private void triggerAlarm(AlarmTask task) {
        System.out.println("Alarm triggered for task: " + task.getTaskName());
        playSound("alarm.wav");  // Use the relative path
    }

    private void playSound(String soundFile) {
        try {
            File soundFilePath = new File(soundFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFilePath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AlarmManager alarmManager = new AlarmManager();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("Enter the number of tasks you want to schedule:");
        int numberOfTasks = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("Enter task name:");
            String taskName = scanner.nextLine();

            System.out.println("Enter alarm time (format: yyyy-MM-dd HH:mm:ss):");
            String alarmTimeString = scanner.nextLine();
            LocalDateTime alarmTime = LocalDateTime.parse(alarmTimeString, formatter);

            alarmManager.addTask(new AlarmTask(taskName, alarmTime));
        }

        System.out.println("Alarm tasks have been scheduled.");
    }
}
