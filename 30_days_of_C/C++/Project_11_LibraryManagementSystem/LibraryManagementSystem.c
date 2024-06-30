#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Book {
    int bookID;
    char title[100];
    char author[50];
};

void addBook(struct Book *books, int *count) {
    printf("Enter book ID: ");
    scanf("%d", &books[*count].bookID);
    printf("Enter book title: ");
    scanf(" %[^\n]", books[*count].title);
    printf("Enter book author: ");
    scanf("%s", books[*count].author);
    (*count)++;
}

void removeBook(struct Book *books, int *count, int bookID) {
    for (int i = 0; i < *count; i++) {
        if (books[i].bookID == bookID) {
            for (int j = i; j < *count - 1; j++) {
                books[j] = books[j + 1];
            }
            (*count)--;
            break;
        }
    }
}

void displayBooks(struct Book *books, int count) {
    for (int i = 0; i < count; i++) {
        printf("Book ID: %d, Title: %s, Author: %s\n", books[i].bookID, books[i].title, books[i].author);
    }
}

int main() {
    struct Book books[100];
    int count = 0;
    int choice, bookID;

    while (1) {
        printf("1. Add Book\n");
        printf("2. Remove Book\n");
        printf("3. Display Books\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addBook(books, &count);
                break;
            case 2:
                printf("Enter book ID to remove: ");
                scanf("%d", &bookID);
                removeBook(books, &count, bookID);
                break;
            case 3:
                displayBooks(books, count);
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
