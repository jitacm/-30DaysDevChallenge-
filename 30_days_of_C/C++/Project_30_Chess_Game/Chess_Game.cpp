#include <iostream>
#include <vector>
#include <string>

using namespace std;

enum PieceType { EMPTY, PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING };
enum Color { NONE, WHITE, BLACK };

struct Piece {
    PieceType type;
    Color color;
};

class ChessBoard {
public:
    ChessBoard();
    void displayBoard();
    bool movePiece(int startX, int startY, int endX, int endY);

private:
    vector<vector<Piece>> board;
    bool isMoveValid(int startX, int startY, int endX, int endY);
    void initializeBoard();
};

ChessBoard::ChessBoard() {
    initializeBoard();
}

void ChessBoard::initializeBoard() {
    board = vector<vector<Piece>>(8, vector<Piece>(8, { EMPTY, NONE }));
    
    
    for (int i = 0; i < 8; ++i) {
        board[1][i] = { PAWN, WHITE };
        board[6][i] = { PAWN, BLACK };
    }
    
    
    board[0][0] = board[0][7] = { ROOK, WHITE };
    board[0][1] = board[0][6] = { KNIGHT, WHITE };
    board[0][2] = board[0][5] = { BISHOP, WHITE };
    board[0][3] = { QUEEN, WHITE };
    board[0][4] = { KING, WHITE };
    
    board[7][0] = board[7][7] = { ROOK, BLACK };
    board[7][1] = board[7][6] = { KNIGHT, BLACK };
    board[7][2] = board[7][5] = { BISHOP, BLACK };
    board[7][3] = { QUEEN, BLACK };
    board[7][4] = { KING, BLACK };
}

void ChessBoard::displayBoard() {
    for (int i = 0; i < 8; ++i) {
        for (int j = 0; j < 8; ++j) {
            switch (board[i][j].type) {
                case PAWN: cout << (board[i][j].color == WHITE ? 'P' : 'p'); break;
                case ROOK: cout << (board[i][j].color == WHITE ? 'R' : 'r'); break;
                case KNIGHT: cout << (board[i][j].color == WHITE ? 'N' : 'n'); break;
                case BISHOP: cout << (board[i][j].color == WHITE ? 'B' : 'b'); break;
                case QUEEN: cout << (board[i][j].color == WHITE ? 'Q' : 'q'); break;
                case KING: cout << (board[i][j].color == WHITE ? 'K' : 'k'); break;
                case EMPTY: cout << '.'; break;
            }
            cout << ' ';
        }
        cout << endl;
    }
}

bool ChessBoard::movePiece(int startX, int startY, int endX, int endY) {
    if (isMoveValid(startX, startY, endX, endY)) {
        board[endX][endY] = board[startX][startY];
        board[startX][startY] = { EMPTY, NONE };
        return true;
    }
    return false;
}

bool ChessBoard::isMoveValid(int startX, int startY, int endX, int endY) {
   
    if (board[startX][startY].type == EMPTY) return false;
    if (board[endX][endY].color == board[startX][startY].color) return false;
    return true;
}

int main() {
    ChessBoard game;
    game.displayBoard();
    
    int startX, startY, endX, endY;
    while (true) {
        cout << "Enter move (startX startY endX endY): ";
        cin >> startX >> startY >> endX >> endY;
        if (game.movePiece(startX, startY, endX, endY)) {
            game.displayBoard();
        } else {
            cout << "Invalid move!" << endl;
        }
    }
    
    return 0;
}
