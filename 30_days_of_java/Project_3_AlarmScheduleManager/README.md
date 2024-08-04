# Alarm Schedule Manager

## Introduction

The Alarm Schedule Manager is a Java-based application designed to help you manage and schedule multiple tasks along with their respective alarm times. The program features a graphical user interface (GUI) to provide an intuitive and user-friendly experience for scheduling alarms and managing tasks.

## Features

- **Graphical User Interface**: Intuitive and visually appealing GUI for managing tasks and alarms.
- **User Input for Tasks**: Easily input multiple tasks with their respective alarm dates and times.
- **Task Scheduling**: Automatically schedules tasks using the system clock.
- **Alarm Triggering**: Triggers an alarm with an audible alert at the specified time.
- **Sound Playback**: Plays an alarm sound to notify you of scheduled tasks.
- **Color-Coded Task Panels**: Visually distinct panels for different tasks with hover effects.
- **Scrollable Task List**: Ability to view multiple tasks in a scrollable list.

## How It Works

1. **Initialization**: The program initializes a list of tasks and a timer for scheduling alarms.
2. **Adding Tasks**: Users can add tasks with specific names, dates, and times through the GUI.
3. **Task Scheduling**: The program calculates the delay until the alarm time and schedules it.
4. **Triggering Alarms**: When the alarm time is reached, a message is displayed in the GUI and an alarm sound is played.
5. **Playing Sound**: The program uses the Java Sound API to play the alarm sound from a specified file path.

## Usage

1. **Compile the Java Files**:

    ```bash
    javac AlarmTask.java AlarmManager.java AlarmManagerGUI.java
    ```

2. **Run the Program**:

    ```bash
    java AlarmManagerGUI
    ```

3. **Enter Task Details**:

    - Enter the task name, alarm date (in `yyyy-MM-dd` format), and alarm time (in `HH:mm` format).
    - Click the "Add Task" button to schedule the alarm.

## Requirements

- Java Development Kit (JDK) 8 or higher
- `alarm.wav` file for sound playback (place it in the same directory as your Java files or specify the absolute path in the code)

## Example

1. **Open the Application**:

    ![Alarm Manager GUI].

   ![image](https://github.com/user-attachments/assets/8b55051a-3801-439c-8827-d1ecd0cb1488)


3. **Enter Task Details**:

    - Task Name: `Task 1`
    - Alarm Date: `2024-08-04`
    - Alarm Time: `14:30`

    Click the "Add Task" button.

4. **Scheduled Task**:

    The task will be displayed in the scrollable task area with the specified date and time.

## Code Structure

### AlarmTask.java

Defines the `AlarmTask` class to represent individual tasks with a name and alarm time.

### AlarmManager.java

Manages the scheduling and triggering of alarms. It handles adding tasks and playing the alarm sound at the specified time.

### AlarmManagerGUI.java

Provides the graphical user interface for the application. It includes input fields for task details, buttons for adding tasks, and a display area for scheduled tasks.
