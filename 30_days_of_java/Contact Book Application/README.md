# Contact Book Application

## Description

The Contact Book Application is a Java-based desktop application that allows users to manage their personal contacts through an easy-to-use graphical user interface (GUI). Users can add, edit, delete, and search for contacts while organizing them into categories. The application ensures that all contact data is stored persistently and is available even after the application is closed.

![image](https://github.com/user-attachments/assets/f35e2190-a1a4-4443-8210-a8b912f7aff6)

## Features

- **Add, Edit, Delete Contacts:** Easily manage contact entries.
- **Store Contact Information:** Save essential details like name, phone number, and email.
- **Search for Contacts:** Quickly find contacts using search functionality.
- **Group Contacts:** Organize contacts into categories for better management.
- **Persistent Data Storage:** Data is saved automatically and loaded on the next startup.

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>

2. Navigate to the project directory:
   ```bash
   cd contact-book-application

3. Compile the Java files:
   ```bash
   javac src/*.java
4. Run the application:
   ```bash
   java src/Main

## Usage

- *Add Contact:* Click the "Add Contact" button and fill in the contact details in the form that appears.
- *Edit Contact:* Select a contact from the list and click the "Edit Contact" button. Modify the details and save.
- *Delete Contact:* Select a contact from the list and click the "Delete Contact" button.
- *Search Contact:* Enter a keyword in the search bar to find specific contacts.

## File Structure

`src/`

- *Contact.java:* Represents a single contact.
- *ContactBook.java:* Manages the collection of contacts.
- *ContactBookGUI.java:* Handles the graphical user interface.
- *Main.java:* Entry point of the application.
