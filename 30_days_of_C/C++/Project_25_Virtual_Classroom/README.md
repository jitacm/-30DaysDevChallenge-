# Virtual Classroom System

This is a simple virtual classroom system implemented in C++. The system allows you to manage classes, add students to classes, and view the students in each class.

## Features

- **Add Class**: Add new classes with a class name.
- **Add Student**: Add students to a specified class with their name and email.
- **View Students**: View the students in a specified class.

## How to Run

1. **Clone the Repository**

    ```sh
    git clone https://github.com/yourusername/virtual-classroom-system.git
    cd virtual-classroom-system
    ```

2. **Compile the Code**

    Open a terminal and navigate to the directory containing the source code. Run the following command to compile the code:

    ```sh
    g++ -o virtual_classroom virtual_classroom.cpp
    ```

3. **Run the Program**

    After compiling, run the executable:

    ```sh
    ./virtual_classroom
    ```

4. **Use the System**

    Follow the on-screen instructions to use the virtual classroom system. You can add classes, add students, and view students.

## Code Overview

### Classes

- `Student`: Represents a student with a name and email.
- `Classroom`: Represents a classroom with a name and a list of students. Contains methods to add students and view students.

### Global Variables

- `classrooms`: A vector to store all the classrooms.

### Functions

- `addClassroom()`: Adds a new classroom.
- `addStudent()`: Adds a student to a specified classroom.
- `viewStudents()`: Displays the students in a specified classroom.

### Main Function

The `main()` function displays a menu with options to:
1. Add Class
2. Add Student
3. View Students
4. Exit

## Example

