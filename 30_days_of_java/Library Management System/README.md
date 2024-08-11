# Library Management System

## Overview

The Library Management System is a Java-based application designed to help librarians manage books, members, and lending operations efficiently. It provides a user-friendly graphical interface for easy interaction with the system, suitable for small to medium-sized libraries.

![image](https://github.com/user-attachments/assets/0e3157ed-75e5-4c38-80eb-a685e45e10d0)

## Features

- Book Management:
  - Add new books to the library inventory
  - View list of available books
  - View list of issued books and their borrowers
- Member Management:
  - Register new library members
  - View complete list of library members
- Lending Operations:
  - Lend books to registered members
  - Process book returns
  - Prevent lending of unavailable books
- User-friendly GUI for all operations
- Real-time updates of book and member status

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Java IDE (e.g., IntelliJ IDEA, Eclipse, or NetBeans)

## Project Structure

The project is organized into several key classes:

- `Book`: Represents a book in the library.
- `Member`: Represents a library member.
- `Library`: Manages the collection of books and members, and handles lending operations.
- `LibraryGUI`: Provides the graphical user interface for interacting with the system.
- `LibraryManagementSystem`: Contains the main method to launch the application.

## Running the Application

1. Locate the `LibraryManagementSystem` class in your IDE.
2. Run the `main` method in this class.
3. The application GUI will launch, presenting you with input fields and action buttons.

## Detailed Usage Guide

### Adding a Book

1. Enter the book's ISBN in the "ISBN" field.
2. Enter the book's title in the "Title" field.
3. Enter the author's name in the "Author" field.
4. Click the "Add Book" button.
5. A success or error message will be displayed in the output area.

### Registering a Member

1. Enter a unique member ID in the "Member ID" field.
2. Enter the member's name in the "Member Name" field.
3. Click the "Add Member" button.
4. A success or error message will be displayed in the output area.

### Lending a Book

1. Enter the book's ISBN in the "ISBN" field.
2. Enter the member's ID in the "Member ID" field.
3. Click the "Lend Book" button.
4. The result of the operation will be displayed in the output area.

### Returning a Book

1. Enter the book's ISBN in the "ISBN" field.
2. Enter the member's ID in the "Member ID" field.
3. Click the "Return Book" button.
4. The result of the operation will be displayed in the output area.

### Viewing Available Books

1. Click the "Available Books" button.
2. A list of all available books will be displayed in the output area.

### Viewing Issued Books

1. Click the "Issued Books" button.
2. A list of all issued books, along with the borrower's information, will be displayed in the output area.

### Viewing Member List

1. Click the "Member List" button.
2. A list of all registered members will be displayed in the output area.

## Error Handling

- The system prevents adding duplicate books (books with the same ISBN, title, and author).
- Members with the same name can be added as long as they have unique IDs.
- The system checks for book availability before lending.
- Appropriate error messages are displayed for invalid operations.

## Data Persistence

Please note that in the current version, data is not persisted between sessions. All data is held in memory and will be lost when the application is closed. Future versions may implement database integration for persistent storage.

## Limitations and Future Enhancements

- Current version does not include data persistence.
- Search functionality for books and members is not implemented.
- The system does not track due dates or generate overdue notifications.
- Reporting features (e.g., most popular books, active members) are not available.
