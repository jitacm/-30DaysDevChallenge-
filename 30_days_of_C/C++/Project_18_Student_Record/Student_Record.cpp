#include <iostream>
#include <vector>
#include <string>
#include <iomanip>
#include <algorithm>

using namespace std;

class Student {
private:
    int rollNumber;
    string name;
    string course;
    double marks;

public:
    Student(int rollNumber, const string& name, const string& course, double marks)
        : rollNumber(rollNumber), name(name), course(course), marks(marks) {}

    int getRollNumber() const {
        return rollNumber;
    }

    string getName() const {
        return name;
    }

    string getCourse() const {
        return course;
    }

    double getMarks() const {
        return marks;
    }

    void displayStudentDetails() const {
        cout << setw(10) << rollNumber << setw(20) << name << setw(20) << course << setw(10) << marks << endl;
    }
};

class StudentRecordSystem {
private:
    vector<Student> students;
    int nextRollNumber;

public:
    StudentRecordSystem() : nextRollNumber(1) {}

    void addStudent(const string& name, const string& course, double marks) {
        Student newStudent(nextRollNumber++, name, course, marks);
        students.push_back(newStudent);
        cout << "Student added successfully! Roll Number: " << newStudent.getRollNumber() << endl;
    }

    void viewStudent(int rollNumber) const {
        auto it = find_if(students.begin(), students.end(), [&](const Student& student) {
            return student.getRollNumber() == rollNumber;
        });

        if (it != students.end()) {
            cout << setw(10) << "Roll No" << setw(20) << "Name" << setw(20) << "Course" << setw(10) << "Marks" << endl;
            it->displayStudentDetails();
        } else {
            cout << "Student not found!" << endl;
        }
    }

    void displayAllStudents() const {
        cout << "All Students:\n";
        cout << setw(10) << "Roll No" << setw(20) << "Name" << setw(20) << "Course" << setw(10) << "Marks" << endl;
        for (const auto& student : students) {
            student.displayStudentDetails();
        }
        cout << endl;
    }
};

int main() {
    StudentRecordSystem system;
    int choice;

    do {
        cout << "\nStudent Record System\n";
        cout << "1. Add Student\n";
        cout << "2. View Student\n";
        cout << "3. Display All Students\n";
        cout << "4. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1: {
                string name, course;
                double marks;
                cout << "Enter student name: ";
                cin.ignore();
                getline(cin, name);
                cout << "Enter course: ";
                getline(cin, course);
                cout << "Enter marks: ";
                cin >> marks;
                system.addStudent(name, course, marks);
                break;
            }
            case 2: {
                int rollNumber;
                cout << "Enter roll number: ";
                cin >> rollNumber;
                system.viewStudent(rollNumber);
                break;
            }
            case 3:
                system.displayAllStudents();
                break;
            case 4:
                cout << "Exiting the program.\n";
                break;
            default:
                cout << "Invalid choice. Please enter a valid choice.\n";
        }

    } while (choice != 4);

    return 0;
}
