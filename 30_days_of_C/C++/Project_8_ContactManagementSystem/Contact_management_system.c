#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Contact {
    char name[50];
    char phone[15];
    char email[50];
};

void addContact(struct Contact *contacts, int *count) {
    printf("Enter name: ");
    scanf("%s", contacts[*count].name);
    printf("Enter phone number: ");
    scanf("%s", contacts[*count].phone);
    printf("Enter email: ");
    scanf("%s", contacts[*count].email);
    (*count)++;
}

void deleteContact(struct Contact *contacts, int *count, char *name) {
    for (int i = 0; i < *count; i++) {
        if (strcmp(contacts[i].name, name) == 0) {
            for (int j = i; j < *count - 1; j++) {
                contacts[j] = contacts[j + 1];
            }
            (*count)--;
            break;
        }
    }
}

void displayContacts(struct Contact *contacts, int count) {
    for (int i = 0; i < count; i++) {
        printf("Name: %s, Phone: %s, Email: %s\n", contacts[i].name, contacts[i].phone, contacts[i].email);
    }
}

int main() {
    struct Contact contacts[100];
    int count = 0;
    int choice;
    char name[50];

    while (1) {
        printf("1. Add Contact\n");
        printf("2. Delete Contact\n");
        printf("3. Display Contacts\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addContact(contacts, &count);
                break;
            case 2:
                printf("Enter name to delete: ");
                scanf("%s", name);
                deleteContact(contacts, &count, name);
                break;
            case 3:
                displayContacts(contacts, count);
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
