# Habit Tracker with Alarm Reminder

## Overview

The Habit Tracker with Alarm Reminder is a simple Java-based application that helps you build and maintain daily habits. It allows you to define habits, track their completion, view statistics on your progress, and set reminders that trigger an alarm sound at a specified time.

![Screenshot 2024-08-14 020856](https://github.com/user-attachments/assets/3a282e3b-9bfe-4661-9aa0-b8fc9b91ca8b)

## Features

- **Habit Management**: Add, remove, and track daily habits.
- **Completion Tracking**: Record the completion of habits each day and track your streaks.
- **Reminder System**: Set a specific time for each habit, which triggers an alarm (`alarm.wav`) and a notification.
- **Persistent Data**: Habit data is saved and loaded automatically, ensuring that your progress is not lost when the application is closed.

## Installation

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/habit-tracker.git
    ```
2. **Navigate to the Project Directory**:
    ```bash
    cd habit-tracker
    ```
3. **Compile the Java Code**:
    ```bash
    javac -d bin src/*.java
    ```
4. **Run the Application**:
    ```bash
    java -cp bin HabitTrackerApp
    ```

## Usage

1. **Adding a Habit**:
    - Click the "Add Habit" button.
    - Enter the habit name in the prompt and confirm.

2. **Completing a Habit**:
    - Select the habit from the list and click "Complete Habit".
    - The habit will be marked as completed for the current day.

3. **Viewing Statistics**:
    - Select a habit from the list and click "View Stats".
    - A dialog will display your current streak and total completions.

4. **Setting a Reminder**:
    - Select a habit from the list and click "Set Reminder".
    - Enter the reminder time in `HH:MM` format.
    - When the reminder time is reached, an alarm will sound, and a notification will be displayed.

## Alarm Sound

- The alarm sound is stored in the `resources` directory as `alarm.wav`.
- Ensure that this file is present for the alarm feature to work.
