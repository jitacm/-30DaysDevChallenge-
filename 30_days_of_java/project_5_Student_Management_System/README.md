# Student Management System

A simple Java-based application for managing students and courses.

## Features

* Add and remove students
* Add courses
* Enroll and unenroll students from courses
* Display student and course information

## Usage

1. Run the `StudentManagementSystem.java` file.
2. Follow the prompts to add students, courses, and enroll/unenroll students.

## Classes

* `Student`: Represents an individual student with attributes like id, name, and email.
* `Course`: Represents a course with attributes like course name and a list of enrolled students.
* `StudentManagementSystem`: Manages students and courses, providing methods to add, remove, enroll, and display information.

## Methods

* `addStudent(int id, String name, String email)`: Adds a new student.
* `removeStudent(int id)`: Removes a student by id.
* `addCourse(String courseName)`: Adds a new course.
* `enrollStudentInCourse(int studentId, String courseName)`: Enrolls a student in a course.
* `unenrollStudentFromCourse(int studentId, String courseName)`: Unenrolls a student from a course.
* `displayStudentInfo(int studentId)`: Displays information about a student.
* `displayCourseInfo(String courseName)`: Displays information about a course.

