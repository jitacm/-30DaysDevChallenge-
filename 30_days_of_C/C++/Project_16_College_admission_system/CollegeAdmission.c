#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STUDENTS 100

typedef struct {
    char name[50];
    int age;
    float marks;
    char department[50];
    int admissionStatus; 
} Student;

Student students[MAX_STUDENTS];
int studentCount = 0;

void addStudent() {
    if (studentCount >= MAX_STUDENTS) {
        printf("Cannot add more students.\n");
        return;
    }

    Student newStudent;
    printf("Enter student name: ");
    scanf("%s", newStudent.name);
    printf("Enter student age: ");
    scanf("%d", &newStudent.age);
    printf("Enter student marks: ");
    scanf("%f", &newStudent.marks);
    printf("Enter desired department: ");
    scanf("%s", newStudent.department);
    newStudent.admissionStatus = 0; 

    students[studentCount] = newStudent;
    studentCount++;
    printf("Student added successfully.\n");
}

void displayStudents() {
    if (studentCount == 0) {
        printf("No students added yet.\n");
        return;
    }

    printf("List of Students:\n");
    for (int i = 0; i < studentCount; i++) {
        printf("Student Name: %s, Age: %d, Marks: %.2f, Department: %s, Admission Status: %s\n",
               students[i].name, students[i].age, students[i].marks, students[i].department,
               students[i].admissionStatus ? "Admitted" : "Not Admitted");
    }
}

void processAdmission() {
    for (int i = 0; i < studentCount; i++) {
        if (students[i].marks > 70) {
            students[i].admissionStatus = 1;
        }
    }
    printf("Admission process completed.\n");
}

int main() {
    int choice;
    while (1) {
        printf("\nCollege Admission System\n");
        printf("1. Add Student\n");
        printf("2. Display Students\n");
        printf("3. Process Admission\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                displayStudents();
                break;
            case 3:
                processAdmission();
                break;
            case 4:
                printf("Exiting the program. Goodbye!\n");
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
