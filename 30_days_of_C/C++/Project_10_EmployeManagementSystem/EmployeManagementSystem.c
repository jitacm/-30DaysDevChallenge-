#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Employee {
    int id;
    char name[50];
    float salary;
};

void addEmployee(struct Employee *employees, int *count) {
    printf("Enter employee ID: ");
    scanf("%d", &employees[*count].id);
    printf("Enter employee name: ");
    scanf("%s", employees[*count].name);
    printf("Enter employee salary: ");
    scanf("%f", &employees[*count].salary);
    (*count)++;
}

void deleteEmployee(struct Employee *employees, int *count, int id) {
    for (int i = 0; i < *count; i++) {
        if (employees[i].id == id) {
            for (int j = i; j < *count - 1; j++) {
                employees[j] = employees[j + 1];
            }
            (*count)--;
            break;
        }
    }
}

void displayEmployees(struct Employee *employees, int count) {
    for (int i = 0; i < count; i++) {
        printf("ID: %d, Name: %s, Salary: %.2f\n", employees[i].id, employees[i].name, employees[i].salary);
    }
}

int main() {
    struct Employee employees[100];
    int count = 0;
    int choice, id;

    while (1) {
        printf("1. Add Employee\n");
        printf("2. Delete Employee\n");
        printf("3. Display Employees\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addEmployee(employees, &count);
                break;
            case 2:
                printf("Enter employee ID to delete: ");
                scanf("%d", &id);
                deleteEmployee(employees, &count, id);
                break;
            case 3:
                displayEmployees(employees, count);
                break;
            case 4:
                exit(0);
                break;
            default:
                printf("Invalid choice!\n");
        }
    }

    return 0;
}
