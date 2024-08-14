# Personal Diary/Journal Application

## Overview

The Personal Diary/Journal Application is a desktop application built using Java and Swing. It allows users to create, manage, and search through personal diary entries. Each entry includes a date, content, tags, and mood tracking. The application ensures data persistence using Java Serialization and I/O, so entries are retained even after the application is closed.

![image](https://github.com/user-attachments/assets/a2100cdd-b9da-4fa8-b08f-71e9a97be91e)

## Features

- **Create Dated Entries:** Write new diary entries with the ability to set or auto-populate the current date.
- **Tagging System:** Add tags to entries for easy searching and organization.
- **Mood Tracking:** Track your mood with each entry, choosing from predefined options or adding custom ones.
- **Search Functionality:** Search through your diary by date, tags, or mood.
- **Data Persistence:** All entries are saved to a file using serialization, so they remain intact across sessions.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>

2. Navigate to the project directory:
   ```bash
   cd personal-diary-journal

3. Compile the Java files:
   ```bash
   javac -d bin src/*.java

4. Run the application:
   ```bash
   java -cp bin Main

## Usage

### Create a New Entry:

- Click on the "New Entry" button to open the entry window.
- Fill in the date, content, tags (comma-separated), and mood.
- Click "Save Entry" to save it.

### Search Entries:

- Click on the "Search Entries" button.
- Enter a tag or mood to search by.
- The results will be displayed in the search window.

### Save & Exit:

Click the "Save & Exit" button to save all entries and close the application.
