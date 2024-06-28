#include <stdio.h>

char board[3][3];
char current_marker;
char current_player;

void initialize_board() {
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            board[i][j] = ' ';
}

void print_board() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%c ", board[i][j]);
            if (j < 2) printf("| ");
        }
        printf("\n");
        if (i < 2) printf("---------\n");
    }
}

int place_marker(int row, int col) {
    if (board[row][col] != ' ')
        return 0;

    board[row][col] = current_marker;
    return 1;
}

int check_winner() {
    // Check rows
    for (int i = 0; i < 3; i++)
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ')
            return 1;

    // Check columns
    for (int i = 0; i < 3; i++)
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')
            return 1;

    // Check diagonals
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
        return 1;
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')
        return 1;

    return 0;
}

void swap_player_and_marker() {
    if (current_marker == 'X')
        current_marker = 'O';
    else
        current_marker = 'X';

    if (current_player == '1')
        current_player = '2';
    else
        current_player = '1';
}

int main() {
    initialize_board();
    current_marker = 'X';
    current_player = '1';

    for (int i = 0; i < 9; i++) {
        int row, col;

        printf("Player %c, enter row and column: ", current_player);
        scanf("%d %d", &row, &col);

        if (place_marker(row, col) == 0) {
            printf("Invalid move. Try again.\n");
            i--;
            continue;
        }

        print_board();

        if (check_winner() == 1) {
            printf("Player %c wins!\n", current_player);
            return 0;
        }

        swap_player_and_marker();
    }

    printf("It's a draw!\n");
    return 0;
}
