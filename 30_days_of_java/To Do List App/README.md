# Java To-Do List Application

A simple, intuitive To-Do List application built with Java and Swing GUI.

![image](https://github.com/user-attachments/assets/55cbe252-6c47-457b-b65a-5ca99c70ec78)

## Features

- Add new tasks
- Remove existing tasks
- Toggle task completion status
- Persistent storage of tasks
- User-friendly graphical interface

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor

## Installation

1. Clone the repository or download the source files:
   - Task.java
   - ToDoList.java
   - GUI.java

2. Compile the Java files:

    `javac Task.java ToDoList.java GUI.java`

3. Run the application:

    `java GUI.java`

## Usage

1. **Adding a task**: 
Enter the task description in the text field at the top of the window and click the "Add Task" button or press Enter.

2. **Removing a task**: 
Select a task from the list and click the "Remove Task" button.

3. **Toggling task completion**: 
Select a task from the list and click the "Toggle Completion" button. Completed tasks will be marked with [X].

4. **Closing the application**: 
Simply close the window. Your tasks will be automatically saved.

## Data Persistence

The application uses Java serialization to store tasks in a file named `todolist.ser` in the same directory as the application. This file is automatically created when you add your first task and is updated whenever you make changes to your task list.
