# Personal Library Catalog

A Java application with a graphical user interface for managing a personal library catalog. This application allows you to store and manage your book collection, with data persistence across sessions.

![Screenshot 2024-08-13 012851](https://github.com/user-attachments/assets/f0d2afb4-697f-4eaf-bb49-1ed91cbd0b20)

## Features

- Add books to your personal library
- Track borrowed and returned books
- Search for books by title, author, or genre
- Rate and review books
- Data persistence: your library is saved and loaded automatically

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Running the Application

1. **Clone the repository or download the source code.**

2. **Navigate to the project directory.**

3. **Compile the Java files:**

   `javac *.java`

4. **Run the application:**

   `java Main`

## Usage

1. **Adding a Book**: Fill in the title, author, and genre fields, then click "Add Book".
2. **Removing a Book**: Select a book from the list and click "Remove Book".
3. **Borrowing/Returning a Book**: Select a book and click "Borrow Book" or "Return Book".
4. **Searching for Books**: Enter a search term, select the search type (Title, Author, or Genre), and click "Search".
5. **Rating and Reviewing**: Select a book, set the rating using the spinner, enter a review in the text area, and click "Add Book" to update.

Your library data is automatically saved when you close the application and loaded when you start it again.

## Data Persistence

The application uses file-based serialization to store your library data. The data is saved in a file named `library.dat` in the same directory as the application. This file is automatically created if it doesn't exist, and is updated whenever you make changes to your library.
