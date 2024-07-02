#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>

void displayTime() {
    time_t rawtime;
    struct tm * timeinfo;

    time(&rawtime);
    timeinfo = localtime(&rawtime);

    printf("\r%02d:%02d:%02d", timeinfo->tm_hour, timeinfo->tm_min, timeinfo->tm_sec);
    fflush(stdout);
}

int main() {
    while (1) {
        displayTime();
        sleep(1);  // Sleep for 1 second
    }
    return 0;
}
