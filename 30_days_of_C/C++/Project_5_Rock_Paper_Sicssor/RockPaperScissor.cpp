#include <cstdlib>
#include <ctime>
#include <iostream>
using namespace std;
char getComputerMove()
{

    int move;
    srand(time(NULL));
    move = rand() % 3;
    if (move == 0)
    {
        return 'p';
    }
    else if (move == 1)
    {
        return 's';
    }
    return 'r';
}
int getResults(char playerMove, char computerMove)
{
    // condition for draw
    if (playerMove == computerMove)
    {
        return 0;
    }
    if (playerMove == 's' && computerMove == 'p')
    {
        return 1;
    }
    if (playerMove == 's' && computerMove == 'r')
    {
        return -1;
    }
    if (playerMove == 'p' && computerMove == 'r')
    {
        return 1;
    }
    if (playerMove == 'p' && computerMove == 's')
    {
        return -1;
    }
    if (playerMove == 'r' && computerMove == 'p')
    {
        return -1;
    }
    if (playerMove == 'r' && computerMove == 's')
    {
        return 1;
    }

    return 0;
}
int main()
{

    char playerMove;

    cout << "\n\n\n\t\t\tWelcome to Stone Paper Scissor "
            "Game\n";

    cout << "\n\t\tEnter r for ROCK, p for PAPER, and s "
            "for SCISSOR\n\t\t\t\t\t";
    while (1)
    {
        cin >> playerMove;
        if (playerMove == 'p' || playerMove == 'r' || playerMove == 's')
        {
            break;
        }
        else
        {
            cout << "\t\t\tInvalid Player Move!!! Please Try Again." << endl;
        }
    }
    char computerMove = getComputerMove();

    int result = getResults(playerMove, computerMove);

    if (result == 0)
    {
        cout << "\n\t\t\tGame Draw!\n";
    }
    else if (result == 1)
    {
        cout << "\n\t\t\tCongratulations! Player won the "
                "game!\n";
    }
    else
    {
        cout << "\n\t\t\tOh! Computer won the game!\n";
    }
    cout << "\t\t\tYour Move: " << playerMove << endl;
    cout << "\t\t\tComputer's Move: " << computerMove << endl;

    return 0;
}