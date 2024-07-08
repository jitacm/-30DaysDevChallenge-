#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_JOBS 100
#define MAX_APPLICANTS 100
#define MAX_NAME_LENGTH 50

typedef struct {
    char name[MAX_NAME_LENGTH];
    char description[200];
    int numApplicants;
    char applicants[MAX_APPLICANTS][MAX_NAME_LENGTH];
} Job;

Job jobs[MAX_JOBS];
int jobCount = 0;

void addJob() {
    if (jobCount >= MAX_JOBS) {
        printf("Cannot add more jobs.\n");
        return;
    }

    Job newJob;
    printf("Enter job name: ");
    scanf(" %[^\n]%*c", newJob.name);
    printf("Enter job description: ");
    scanf(" %[^\n]%*c", newJob.description);
    newJob.numApplicants = 0;

    jobs[jobCount] = newJob;
    jobCount++;
    printf("Job added successfully.\n");
}

void viewJobs() {
    if (jobCount == 0) {
        printf("No jobs available.\n");
        return;
    }

    printf("Available Jobs:\n");
    for (int i = 0; i < jobCount; i++) {
        printf("%d. %s - %s\n", i + 1, jobs[i].name, jobs[i].description);
    }
}

void addApplicant() {
    if (jobCount == 0) {
        printf("No jobs available to apply for.\n");
        return;
    }

    int jobIndex;
    viewJobs();
    printf("Enter job number to apply for: ");
    scanf("%d", &jobIndex);
    jobIndex--; // Adjusting to array index

    if (jobIndex < 0 || jobIndex >= jobCount) {
        printf("Invalid job number.\n");
        return;
    }

    if (jobs[jobIndex].numApplicants >= MAX_APPLICANTS) {
        printf("Cannot add more applicants for this job.\n");
        return;
    }

    printf("Enter applicant name: ");
    scanf(" %[^\n]%*c", jobs[jobIndex].applicants[jobs[jobIndex].numApplicants]);
    jobs[jobIndex].numApplicants++;
    printf("Applicant added successfully.\n");
}

void viewApplicants() {
    if (jobCount == 0) {
        printf("No jobs available.\n");
        return;
    }

    int jobIndex;
    viewJobs();
    printf("Enter job number to view applicants: ");
    scanf("%d", &jobIndex);
    jobIndex--; // Adjusting to array index

    if (jobIndex < 0 || jobIndex >= jobCount) {
        printf("Invalid job number.\n");
        return;
    }

    if (jobs[jobIndex].numApplicants == 0) {
        printf("No applicants for this job.\n");
        return;
    }

    printf("Applicants for %s:\n", jobs[jobIndex].name);
    for (int i = 0; i < jobs[jobIndex].numApplicants; i++) {
        printf("%d. %s\n", i + 1, jobs[jobIndex].applicants[i]);
    }
}

int main() {
    int choice;
    while (1) {
        printf("\nJob Portal System\n");
        printf("1. Add Job\n");
        printf("2. View Jobs\n");
        printf("3. Add Applicant\n");
        printf("4. View Applicants\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                addJob();
                break;
            case 2:
                viewJobs();
                break;
            case 3:
                addApplicant();
                break;
            case 4:
                viewApplicants();
                break;
            case 5:
                printf("Exiting the program. Goodbye!\n");
                exit(0);
            default:
                printf("Invalid choice. Please try again.\n");
        }
    }

    return 0;
}
