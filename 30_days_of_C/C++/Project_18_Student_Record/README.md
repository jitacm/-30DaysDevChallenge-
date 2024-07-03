# Student Record System

This project is a simple console-based Student Record System written in C++. It allows you to add, view, and display all students' details.

## Features

- Add a student
- View a student's details by roll number
- Display all students

## Requirement
- A C++ compiler (such as g++)

## Usage

1. Clone the repository or download the source code.
2. Open the project folder in your terminal.
3. Compile the program using a C++ compiler. For example, using g++:
    sh
    g++ -o student_record_system main.cpp
    
4. Run the compiled program:
    sh
    ./student_record_system
    

## Program Options

1. *Add Student*: Enter the student's name, course, and marks to add a new student to the system.
2. *View Student*: Enter the roll number of the student to view their details.
3. *Display All Students*: Display all students' details in a tabular format.
4. *Exit*: Exit the program.

## Example

```plaintext
Student Record System
1. Add Student
2. View Student
3. Display All Students
4. Exit
Enter your choice: 1
Enter student name: John Doe
Enter course: Computer Science
Enter marks: 85.5
Student added successfully! Roll Number: 1

Student Record System
1. Add Student
2. View Student
3. Display All Students
4. Exit
Enter your choice: 2
Enter roll number: 1
     Roll No                Name              Course      Marks
          1           John Doe    Computer Science       85.5