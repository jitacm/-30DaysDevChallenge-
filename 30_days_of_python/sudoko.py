import pygame
import sys

# Initialize pygame
pygame.font.init()
pygame.init()

# Screen dimensions
width, height = 600, 700
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption('SUDOKU SOLVER USING BACKTRACKING')
icon = pygame.image.load('icon.png')
pygame.display.set_icon(icon)

# Fonts
font1 = pygame.font.SysFont('comicsans', 40)
font2 = pygame.font.SysFont('comicsans', 20)
font3 = pygame.font.SysFont('comicsans', 30)

# Variables
x = 0
y = 0
dif = 500 / 9
val = 0
paused = False

# Default Sudoku Board
grid = [
    [7, 8, 0, 4, 0, 0, 1, 2, 0],
    [6, 0, 0, 0, 7, 5, 0, 0, 9],
    [0, 0, 0, 6, 0, 1, 0, 7, 8],
    [0, 0, 7, 0, 4, 0, 2, 6, 0],
    [0, 0, 1, 0, 5, 0, 9, 3, 0],
    [9, 0, 4, 0, 6, 0, 0, 0, 5],
    [0, 7, 0, 3, 0, 0, 0, 1, 2],
    [1, 2, 0, 0, 0, 7, 4, 0, 0],
    [0, 4, 9, 2, 0, 6, 0, 0, 7]
]

# Colors for themes
themes = {
    "default": {
        "bg": (255, 255, 255),
        "grid": (0, 0, 0),
        "highlight": (0, 153, 153),
        "box": (255, 0, 0),
        "text": (0, 0, 0),
        "error": (255, 0, 0)
    },
    "dark": {
        "bg": (30, 30, 30),
        "grid": (255, 255, 255),
        "highlight": (50, 168, 82),
        "box": (255, 99, 71),
        "text": (255, 255, 255),
        "error": (255, 69, 0)
    }
}
current_theme = "default"

def get_coord(pos):
    global x, y
    x = pos[0] // dif
    y = pos[1] // dif

def draw_box():
    for i in range(2):
        pygame.draw.line(screen, themes[current_theme]["box"], (x * dif-3, (y + i) * dif), (x * dif + dif + 3, (y + i) * dif), 7)
        pygame.draw.line(screen, themes[current_theme]["box"], ((x + i) * dif, y * dif), ((x + i) * dif, y * dif + dif), 7)

def draw_grid():
    for i in range(9):
        for j in range(9):
            if grid[i][j] != 0:
                pygame.draw.rect(screen, themes[current_theme]["highlight"], (i * dif, j * dif, dif + 1, dif + 1))
                text = font1.render(str(grid[i][j]), True, themes[current_theme]["text"])
                screen.blit(text, (i * dif + 15, j * dif + 15))

    for i in range(10):
        thick = 7 if i % 3 == 0 else 1
        pygame.draw.line(screen, themes[current_theme]["grid"], (0, i * dif), (500, i * dif), thick)
        pygame.draw.line(screen, themes[current_theme]["grid"], (i * dif, 0), (i * dif, 500), thick)

def draw_val(val):
    text = font1.render(str(val), True, themes[current_theme]["text"])
    screen.blit(text, (x * dif + 15, y * dif + 15))

def raise_error_1():
    text = font1.render('WRONG !!!', True, themes[current_theme]["error"])
    screen.blit(text, (20, 570))

def raise_error_2():
    text = font1.render('Invalid Key', True, themes[current_theme]["error"])
    screen.blit(text, (20, 570))

def valid(m, i, j, val):
    for it in range(9):
        if m[i][it] == val or m[it][j] == val:
            return False
    it, jt = i // 3, j // 3
    for i in range(it * 3, it * 3 + 3):
        for j in range(jt * 3, jt * 3 + 3):
            if m[i][j] == val:
                return False
    return True

def solve(grid, i, j):
    while grid[i][j] != 0:
        if i < 8:
            i += 1
        elif i == 8 and j < 8:
            i = 0
            j += 1
        elif i == 8 and j == 8:
            return True

    pygame.event.pump()
    for it in range(1, 10):
        if valid(grid, i, j, it):
            grid[i][j] = it
            global x, y
            x, y = i, j
            screen.fill(themes[current_theme]["bg"])
            draw_grid()
            draw_box()
            pygame.display.update()
            pygame.time.delay(20)
            if solve(grid, i, j):
                return True
            grid[i][j] = 0
            screen.fill(themes[current_theme]["bg"])
            draw_grid()
            draw_box()
            pygame.display.update()
            pygame.time.delay(50)
    return False

def display_message(msg, pos):
    text = font2.render(msg, True, themes[current_theme]["text"])
    screen.blit(text, pos)

def display_instructions():
    display_message('PRESS D TO RESET TO DEFAULT / R TO EMPTY', (20, 520))
    display_message('ENTER VALUES AND PRESS ENTER TO VISUALIZE', (20, 540))
    display_message('P TO PAUSE / RESUME | T TOGGLE THEME', (20, 560))

def result():
    text = font1.render('FINISHED PRESS R or D', True, themes[current_theme]["text"])
    screen.blit(text, (20, 570))

def welcome_screen():
    screen.fill(themes[current_theme]["bg"])
    title = font3.render('WELCOME TO SUDOKU SOLVER!', True, themes[current_theme]["text"])
    start_msg = font2.render('Press ENTER to start solving!', True, themes[current_theme]["text"])
    instructions_msg = font2.render('Instructions: Press I to toggle instructions', True, themes[current_theme]["text"])
    screen.blit(title, (width/2 - title.get_width()/2, height/3))
    screen.blit(start_msg, (width/2 - start_msg.get_width()/2, height/2))
    screen.blit(instructions_msg, (width/2 - instructions_msg.get_width()/2, height/2 + 40))
    pygame.display.update()

# Main loop
run = True
flag_1 = 0
flag_2 = 0
rs = 0
error = 0
show_instructions = False
show_welcome = True

while run:
    if show_welcome:
        welcome_screen()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    show_welcome = False
                if event.key == pygame.K_i:
                    show_instructions = not show_instructions
    else:
        screen.fill(themes[current_theme]["bg"])
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
            if event.type == pygame.MOUSEBUTTONDOWN and not paused:
                flag_1 = 1
                pos = pygame.mouse.get_pos()
                get_coord(pos)
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    x -= 1 if x > 0 else 0
                    flag_1 = 1
                if event.key == pygame.K_RIGHT:
                    x += 1 if x < 8 else 0
                    flag_1 = 1
                if event.key == pygame.K_UP:
                    y -= 1 if y > 0 else 0
                    flag_1 = 1
                if event.key == pygame.K_DOWN:
                    y += 1 if y < 8 else 0
                    flag_1 = 1
                if event.key == pygame.K_1:
                    val = 1
                if event.key == pygame.K_2:
                    val = 2
                if event.key == pygame.K_3:
                    val = 3
                if event.key == pygame.K_4:
                    val = 4
                if event.key == pygame.K_5:
                    val = 5
                if event.key == pygame.K_6:
                    val = 6
                if event.key == pygame.K_7:
                    val = 7
                if event.key == pygame.K_8:
                    val = 8
                if event.key == pygame.K_9:
                    val = 9
                if event.key == pygame.K_RETURN:
                    flag_2 = 1
                if event.key == pygame.K_r:
                    rs = 0
                    error = 0
                    flag_2 = 0
                    grid = [[0 for _ in range(9)] for _ in range(9)]
                if event.key == pygame.K_d:
                    rs = 0
                    error = 0
                    flag_2 = 0
                    grid = [
                        [7, 8, 0, 4, 0, 0, 1, 2, 0],
                        [6, 0, 0, 0, 7, 5, 0, 0, 9],
                        [0, 0, 0, 6, 0, 1, 0, 7, 8],
                        [0, 0, 7, 0, 4, 0, 2, 6, 0],
                        [0, 0, 1, 0, 5, 0, 9, 3, 0],
                        [9, 0, 4, 0, 6, 0, 0, 0, 5],
                        [0, 7, 0, 3, 0, 0, 0, 1, 2],
                        [1, 2, 0, 0, 0, 7, 4, 0, 0],
                        [0, 4, 9, 2, 0, 6, 0, 0, 7]
                    ]
                if event.key == pygame.K_t:
                    current_theme = "dark" if current_theme == "default" else "default"
                if event.key == pygame.K_p:
                    paused = not paused
        if val != 0:
            draw_val(val)
            if valid(grid, int(x), int(y), val):
                grid[int(x)][int(y)] = val
                flag_1 = 0
                val = 0
            else:
                grid[int(x)][int(y)] = 0
                raise_error_1()
                val = 0
        if error == 1:
            raise_error_2()
        if rs == 1:
            result()
        if flag_1 == 1:
            draw_box()
        if flag_2 == 1:
            if solve(grid, 0, 0):
                rs = 1
            else:
                error = 1
            flag_2 = 0
        draw_grid()
        if show_instructions:
            display_instructions()
    pygame.display.update()
