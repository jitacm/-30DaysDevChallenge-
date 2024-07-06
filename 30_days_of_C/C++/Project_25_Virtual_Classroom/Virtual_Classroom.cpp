#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Student {
public:
    string name;
    string email;

    Student(string name, string email) {
        this->name = name;
        this->email = email;
    }
};

class Classroom {
public:
    string className;
    vector<Student> students;

    Classroom(string className) {
        this->className = className;
    }

    void addStudent(string name, string email) {
        Student newStudent(name, email);
        students.push_back(newStudent);
    }

    void viewStudents() {
        if (students.empty()) {
            cout << "No students in this class." << endl;
        } else {
            cout << "Students in " << className << ":\n";
            for (size_t i = 0; i < students.size(); i++) {
                cout << i + 1 << ". " << students[i].name << " (" << students[i].email << ")\n";
            }
        }
    }
};

vector<Classroom> classrooms;

void addClassroom() {
    string className;
    cout << "Enter class name: ";
    cin.ignore();
    getline(cin, className);
    classrooms.push_back(Classroom(className));
    cout << "Class added successfully.\n";
}

void addStudent() {
    if (classrooms.empty()) {
        cout << "No classes available. Add a class first.\n";
        return;
    }

    string className, studentName, studentEmail;
    cout << "Enter class name to add student to: ";
    cin.ignore();
    getline(cin, className);

    for (size_t i = 0; i < classrooms.size(); i++) {
        if (classrooms[i].className == className) {
            cout << "Enter student name: ";
            getline(cin, studentName);
            cout << "Enter student email: ";
            getline(cin, studentEmail);
            classrooms[i].addStudent(studentName, studentEmail);
            cout << "Student added successfully.\n";
            return;
        }
    }

    cout << "Class not found.\n";
}

void viewStudents() {
    if (classrooms.empty()) {
        cout << "No classes available.\n";
        return;
    }

    string className;
    cout << "Enter class name to view students: ";
    cin.ignore();
    getline(cin, className);

    for (size_t i = 0; i < classrooms.size(); i++) {
        if (classrooms[i].className == className) {
            classrooms[i].viewStudents();
            return;
        }
    }

    cout << "Class not found.\n";
}

int main() {
    int choice;
    while (1) {
        cout << "\nVirtual Classroom System\n";
        cout << "1. Add Class\n";
        cout << "2. Add Student\n";
        cout << "3. View Students\n";
        cout << "4. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                addClassroom();
                break;
            case 2:
                addStudent();
                break;
            case 3:
                viewStudents();
                break;
            case 4:
                cout << "Exiting the program. Goodbye!\n";
                exit(0);
            default:
                cout << "Invalid choice. Please try again.\n";
        }
    }

    return 0;
}
