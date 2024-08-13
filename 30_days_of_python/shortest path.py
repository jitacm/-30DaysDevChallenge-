import curses
from curses import wrapper
import queue
import time
import random

# Define maze dimensions
MAZE_WIDTH = 9
MAZE_HEIGHT = 9

# Define colors
BLUE = curses.COLOR_BLUE
RED = curses.COLOR_RED
GREEN = curses.COLOR_GREEN
YELLOW = curses.COLOR_YELLOW
WHITE = curses.COLOR_WHITE

# Create a random maze generator
def generate_maze(width, height):
    maze = [['#' for _ in range(width)] for _ in range(height)]
    start, end = (1, 1), (height - 2, width - 2)
    maze[start[0]][start[1]] = 'O'
    maze[end[0]][end[1]] = 'X'

    for _ in range(width * height // 2):
        y, x = random.randint(1, height - 2), random.randint(1, width - 2)
        if (y, x) != start and (y, x) != end:
            maze[y][x] = ' '

    return maze, start, end

# Define the maze
maze, start_pos, end_pos = generate_maze(MAZE_WIDTH, MAZE_HEIGHT)

def print_maze(maze, stdscr, path=[], status=''):
    BLUE_PAIR = curses.color_pair(1)
    RED_PAIR = curses.color_pair(2)
    GREEN_PAIR = curses.color_pair(3)
    YELLOW_PAIR = curses.color_pair(4)
    WHITE_PAIR = curses.color_pair(5)

    stdscr.clear()
    stdscr.addstr(0, 0, f"Status: {status}", curses.color_pair(4))

    for i, row in enumerate(maze):
        for j, value in enumerate(row):
            char = value
            if (i, j) in path:
                if value == 'X':
                    char = 'E'
                    stdscr.addstr(i + 1, j * 2, char, GREEN_PAIR)
                else:
                    char = '.'
                    stdscr.addstr(i + 1, j * 2, char, RED_PAIR)
            else:
                if value == 'O':
                    stdscr.addstr(i + 1, j * 2, char, YELLOW_PAIR)
                elif value == 'X':
                    stdscr.addstr(i + 1, j * 2, char, GREEN_PAIR)
                else:
                    stdscr.addstr(i + 1, j * 2, char, WHITE_PAIR)
    
    stdscr.refresh()

def find_start(maze, start):
    for i, row in enumerate(maze):
        for j, value in enumerate(row):
            if value == start:
                return i, j
    return None

def find_path(maze, stdscr):
    start = "O"
    end = "X"
    start_pos = find_start(maze, start)
    end_pos = find_start(maze, end)

    q = queue.Queue()
    q.put((start_pos, [start_pos]))

    visited = set()

    while not q.empty():
        current_pos, path = q.get()
        row, col = current_pos

        stdscr.clear()
        print_maze(maze, stdscr, path, status="Finding Path...")
        time.sleep(0.1)
        stdscr.refresh()

        if maze[row][col] == end:
            stdscr.clear()
            print_maze(maze, stdscr, path, status="Path Found!")
            stdscr.getch()  # Wait for user input before closing
            return path

        neighbors = find_neighbors(maze, row, col)
        for neighbor in neighbors:
            if neighbor in visited:
                continue

            r, c = neighbor
            if maze[r][c] == "#":
                continue

            new_path = path + [neighbor]
            q.put((neighbor, new_path))
            visited.add(neighbor)

    return []

def find_neighbors(maze, row, col):
    neighbors = []

    if row > 0:  # UP
        neighbors.append((row - 1, col))
    if row + 1 < len(maze):  # DOWN
        neighbors.append((row + 1, col))
    if col > 0:  # LEFT
        neighbors.append((row, col - 1))
    if col + 1 < len(maze[0]):  # RIGHT
        neighbors.append((row, col + 1))

    return neighbors

def main(stdscr):
    curses.init_pair(1, BLUE, curses.COLOR_BLACK)
    curses.init_pair(2, RED, curses.COLOR_BLACK)
    curses.init_pair(3, GREEN, curses.COLOR_BLACK)
    curses.init_pair(4, YELLOW, curses.COLOR_BLACK)
    curses.init_pair(5, WHITE, curses.COLOR_BLACK)

    while True:
        maze, start_pos, end_pos = generate_maze(MAZE_WIDTH, MAZE_HEIGHT)
        path = find_path(maze, stdscr)
        if not path:
            status = "No Path Found"
        else:
            status = "Path Found"

        stdscr.clear()
        print_maze(maze, stdscr, path, status=status)
        stdscr.addstr(MAZE_HEIGHT + 1, 0, "Press 'Q' to quit or 'R' to restart", curses.color_pair(4))
        stdscr.refresh()

        key = stdscr.getch()
        if key == ord('q'):
            break
        elif key == ord('r'):
            continue

wrapper(main)
