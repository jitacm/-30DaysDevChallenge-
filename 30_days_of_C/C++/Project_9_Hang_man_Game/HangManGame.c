#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void displayWord(char *word, int *guessed) {
    for (int i = 0; i < strlen(word); i++) {
        if (guessed[i]) {
            printf("%c ", word[i]);
        } else {
            printf("_ ");
        }
    }
    printf("\n");
}

int isWordGuessed(int *guessed, int length) {
    for (int i = 0; i < length; i++) {
        if (!guessed[i]) {
            return 0;
        }
    }
    return 1;
}

int main() {
    char word[100];
    int guessed[100] = {0};
    int attempts = 6;
    char guess;

    printf("Enter a word for Hangman: ");
    scanf("%s", word);
    int length = strlen(word);

    system("clear");

    while (attempts > 0) {
        displayWord(word, guessed);
        printf("Attempts remaining: %d\n", attempts);
        printf("Enter a letter: ");
        scanf(" %c", &guess);

        int correct = 0;
        for (int i = 0; i < length; i++) {
            if (word[i] == guess) {
                guessed[i] = 1;
                correct = 1;
            }
        }

        if (!correct) {
            attempts--;
        }

        if (isWordGuessed(guessed, length)) {
            printf("Congratulations! You guessed the word: %s\n", word);
            break;
        }
    }

    if (attempts == 0) {
        printf("Sorry, you've run out of attempts. The word was: %s\n", word);
    }

    return 0;
}
