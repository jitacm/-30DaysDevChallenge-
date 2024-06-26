import random

def create_board(size, num_mines):
    board = [[' ' for _ in range(size)] for _ in range(size)]
    mines = random.sample(range(size * size), num_mines)
    for mine in mines:
        board[mine // size][mine % size] = 'M'
    return board

def display_board(board):
    for row in board:
        print(' '.join(row))
    print()

size = 5
num_mines = 5
board = create_board(size, num_mines)
display_board(board)
