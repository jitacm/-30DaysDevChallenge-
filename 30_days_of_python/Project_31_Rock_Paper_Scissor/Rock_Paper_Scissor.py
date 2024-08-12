import tkinter as tk
from random import choice

class RockPaperScissors:
    def __init__(self):
        self.window = tk.Tk()
        self.window.title("Rock, Paper, Scissors")

        self.player_score = 0
        self.computer_score = 0

        self.player_score_label = tk.Label(self.window, text="Player Score: 0")
        self.player_score_label.pack()

        self.computer_score_label = tk.Label(self.window, text="Computer Score: 0")
        self.computer_score_label.pack()

        self.result_label = tk.Label(self.window, text="")
        self.result_label.pack()

        self.rock_button = tk.Button(self.window, text="Rock", command=self.rock)
        self.rock_button.pack()

        self.paper_button = tk.Button(self.window, text="Paper", command=self.paper)
        self.paper_button.pack()

        self.scissors_button = tk.Button(self.window, text="Scissors", command=self.scissors)
        self.scissors_button.pack()

    def play(self, player_choice):
        computer_choice = choice(["rock", "paper", "scissors"])
        if player_choice == computer_choice:
            self.result_label.config(text="It's a tie!")
        elif (player_choice == "rock" and computer_choice == "scissors") or \
             (player_choice == "paper" and computer_choice == "rock") or \
             (player_choice == "scissors" and computer_choice == "paper"):
            self.player_score += 1
            self.player_score_label.config(text=f"Player Score: {self.player_score}")
            self.result_label.config(text="Player wins!")
        else:
            self.computer_score += 1
            self.computer_score_label.config(text=f"Computer Score: {self.computer_score}")
            self.result_label.config(text="Computer wins!")

    def rock(self):
        self.play("rock")

    def paper(self):
        self.play("paper")

    def scissors(self):
        self.play("scissors")

    def run(self):
        self.window.mainloop()

if __name__ == "__main__":
    game = RockPaperScissors()
    game.run()

