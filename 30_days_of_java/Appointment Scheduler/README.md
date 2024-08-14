# Appointment Scheduler

## Overview

The Appointment Scheduler is a robust application for managing appointments, complete with features like creating, editing, and deleting appointments, setting reminders, and viewing appointments in various formats. Data is saved persistently using serialization.

![Screenshot 2024-08-14 163429](https://github.com/user-attachments/assets/168aab32-cba1-47b5-9558-923adc0f0c1b)

## Features

- **Appointment Management:** Create, edit, and delete appointments via AppointmentPanel.java.
- **Reminder Functionality:** Set and manage reminders using ReminderPanel.java.
- **Calendar View:** View your appointments by day, week, or month in **CalendarPanel.java.
- **Persistent Data Storage:** Save and load appointment data with PersistenceManager.java using serialization.

## Getting Started

### Prerequisites

Java Development Kit (JDK) 11 or higher
An IDE (such as IntelliJ IDEA, Eclipse, etc.)

### Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>

2. **Navigate to the project directory:**
   ```bash
   cd appointment-scheduler/src

3. **Open the project in your IDE and build the project.**

## Usage

1. **Run the application:**

- Start the MainFrame.java to launch the appointment scheduler.

2. **Manage Appointments:**

- Use the AppointmentPanel to create, edit, or delete appointments.

3. **Set Reminders:**

- Configure reminders in the ReminderPanel to keep track of your appointments.

4. **View Appointments:**

- Check your appointments in various formats (day, week, month) through the CalendarPanel.

## Data Persistence

- Data Storage: The PersistenceManager.java handles saving and loading of the DataModel to ensure your appointments and reminders are stored between sessions.
- Serialization: The appointments are saved in a file named appointments.dat.
