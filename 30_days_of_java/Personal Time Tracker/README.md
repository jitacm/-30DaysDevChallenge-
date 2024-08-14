# Personal Time Tracker

## Overview

The Personal Time Tracker is a Java Swing application designed to help users log and track time spent on various activities. The application provides functionality to categorize activities, generate reports on time usage, and set goals for time management.

![image](https://github.com/user-attachments/assets/079bedbe-4b6a-4384-87aa-1c30e761fc69)

## Features

- **Time Logging**: Start and stop timers to track time spent on different activities.
- **Categorization**: Assign activities to categories like Work, Leisure, Study, etc.
- **Report Generation**: Generate reports showing time spent on each activity, formatted in minutes and seconds.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later installed on your machine.
- An IDE or text editor to view and edit the code (e.g., IntelliJ IDEA, Eclipse, or VS Code).

### Installation

1. **Clone the Repository**

   ```bash
   git clone <repository-url>

2. **Navigate to the Project Directory**

   ```bash
   cd personal-time-tracker

3. **Compile the Code**
   Use your IDE to import the project or compile the Java files manually:

   ```bash
   javac -d bin src/*.java

## Running the Application

### 1. Run the Main Application

**Use your IDE to run the MainFrame class or execute the following command:**

   `java -cp bin MainFrame`

## Using the GUI

- Start Timer: Enter a category and click "Start" to begin tracking time.
- Stop Timer: Click "Stop" to end the tracking session.
- Generate Report: Click "Generate Report" to view a summary of time spent on activities.
- Save and Load: Use "Save" and "Load" buttons to manage your time tracker data (note: data persistence may not be fully functional).

## Usage

- Category Input: Type the name of the activity category in the text field and click "Start" to begin.
- Report: The report will be displayed in the text area, showing the time spent on each category in minutes and seconds.
