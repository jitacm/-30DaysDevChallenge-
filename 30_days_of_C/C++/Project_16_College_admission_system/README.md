# College Admission System

This C program manages student admissions by allowing administrators to add students, display student details, and process admissions based on marks.

## Description

The program maintains a list of students with attributes such as name, age, marks, department choice, and admission status. Administrators can add students, view student details, and process admissions automatically based on predefined criteria.

## Features

- **Add Student:** Add a new student with details including name, age, marks, and desired department.
- **Display Students:** View all added students along with their details.
- **Process Admission:** Automatically mark students eligible for admission based on their marks.

## Data Structures

- **Student Struct:** Holds information about each student including name, age, marks, department choice, and admission status.

## How It Works

- **Adding a Student:** The `addStudent()` function prompts the user for student details and adds them to the system.
- **Displaying Students:** The `displayStudents()` function lists all currently added students and their attributes.
- **Processing Admission:** The `processAdmission()` function checks each student's marks and updates their admission status if they meet the criteria.

## Requirements

- This program requires a C compiler to build and run.

## How to Compile and Run

1. **Compile**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the source code (`college_admission.c`).
   - Compile the program using the appropriate command for your C compiler. For example:
     ```bash
     gcc college_admission.c -o college_admission
     ```

2. **Run**
   - After compiling successfully, run the executable:
     ```bash
     ./college_admission
     ```
