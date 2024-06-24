import random
current_player="X"
winner=None
game_running=True
Gameboard=["_","_","_","_","_","_","_","_","_"]
def display():
    print(Gameboard[0], "|", Gameboard[1], "|", Gameboard[2])
    print("_________")
    print(Gameboard[3], "|", Gameboard[4], "|", Gameboard[5])
    print("_________")
    print(Gameboard[6], "|", Gameboard[7], "|", Gameboard[8])

def userinput(Gameboard):
    turn=int(input("Player:"))
    if turn>0 and turn<=9 and Gameboard[turn-1]=="_":
        Gameboard[turn-1]=current_player
    else:
        print("OOPS TRY AGAIN")

def checkHorizontal(Gameboard):
    global winner
    if Gameboard[0]==Gameboard[1]==Gameboard[2]!="_":
        winner=Gameboard[0]
        return True
    elif Gameboard[3]==Gameboard[4]==Gameboard[5]!="_":
        winner = Gameboard[3]
        return True
    elif Gameboard[6]==Gameboard[7]==Gameboard[8]!="_":
        winner = Gameboard[6]
        return True
def checkVertical(Gameboard):
    global winner
    if Gameboard[0] == Gameboard[3] == Gameboard[6] != "_":
        winner = Gameboard[0]
        return True
    elif Gameboard[1] == Gameboard[4] == Gameboard[7] != "_":
        winner = Gameboard[1]
        return True
    elif Gameboard[2] == Gameboard[5] == Gameboard[8] != "_":
        winner = Gameboard[2]
        return True
def checkDiagonal(Gameboard):
    global winner
    if Gameboard[0] == Gameboard[4] == Gameboard[8] != "_":
        winner = Gameboard[0]
        return True
    elif Gameboard[2] == Gameboard[4] == Gameboard[6] != "_":
        winner = Gameboard[3]
        return True
def checkWin(Gameboard):
    checkHorizontal(Gameboard)
    checkVertical(Gameboard)
    checkDiagonal(Gameboard)
    global game_running
    global winner
    if checkHorizontal(Gameboard) or checkVertical(Gameboard) or checkDiagonal(Gameboard)==True:
        print("The winner is: ",winner)
        game_running=False

def checkTie(Gameboard):
    global game_running
    if "_" not in Gameboard:
        print("It is a tie")
        game_running=False

def switchPlayer(Gameboard):
    global current_player
    if current_player == "X":
        current_player = "O"
    else:
        current_player = "X"

def computer(Gameboard):
    while current_player=="O":
        pos=random.randint(0,8)
        if Gameboard[pos]=='_':
            Gameboard[pos]="O"
        else:
            pos = random.randint(0, 8)
            if Gameboard[pos] == '_':
                Gameboard[pos] = "O"
        switchPlayer(Gameboard)

while game_running==True:
    display()
    userinput(Gameboard)
    checkWin(Gameboard)
    checkTie(Gameboard)
    switchPlayer(Gameboard)
    computer(Gameboard)
display()