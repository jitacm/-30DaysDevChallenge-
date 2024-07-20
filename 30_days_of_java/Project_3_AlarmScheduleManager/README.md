# Alarm Schedule Manager

## Introduction

The Alarm Schedule Manager is a Java-based program designed to help you manage and schedule multiple tasks along with their respective alarm times. By integrating with the system clock, this program allows you to set alarms that trigger at specific times, providing an audible alert to remind you of important tasks.

## Features

- **User Input for Tasks**: Easily input multiple tasks with their respective alarm times.
- **Task Scheduling**: Automatically schedules tasks using the system clock.
- **Alarm Triggering**: Triggers an alarm with an audible alert at the specified time.
- **Sound Playback**: Plays an alarm sound to notify you of scheduled tasks.

## How It Works

1. **Initialization**: The program initializes a list of tasks and a timer for scheduling alarms.
2. **Adding Tasks**: Users can add tasks with specific names and alarm times.
3. **Task Scheduling**: The program calculates the delay until the alarm time and schedules it.
4. **Triggering Alarms**: When the alarm time is reached, a message is printed and an alarm sound is played.
5. **Playing Sound**: The program uses the Java Sound API to play the alarm sound from a specified file path.

## Usage

1. **Compile the Java Files**:

   javac AlarmTask.java AlarmManager.java
2. **Run the Program**:

    java AlarmManager

3. **Enter Task Details**: 
    
    Follow the prompts to enter the number of tasks, task names, and alarm times.

## Requirements
- Java Development Kit (JDK) 8 or higher

- alarm.wav file for sound playback (place it in the same directory as your Java files or specify the absolute path in the code)


## Example

Enter the number of tasks you want to schedule:
2

Enter task name:
Task 1

Enter alarm time (format: yyyy-MM-dd HH:mm:ss):
2024-07-04 14:30:00

Enter task name:
Task 2

Enter alarm time (format: yyyy-MM-dd HH:mm:ss):
2024-07-04 15:00:00

Alarm tasks have been scheduled.


