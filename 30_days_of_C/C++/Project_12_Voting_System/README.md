# Voting System for Candidates

This program allows users to manage a voting system for multiple candidates. Each candidate is assigned a unique symbol, and voters can cast their votes accordingly. After all votes are cast, the program displays the winner based on the majority vote.

## Features

- Add candidates with unique symbols.
- Display all candidates with their symbols.
- Allow voters to cast votes for their chosen candidates.
- Calculate and display the winner based on the majority vote.

## How to Use

1. **Setting Up Candidates**
   - Start the program and enter the number of candidates.
   - For each candidate, choose an available symbol and enter their name.

2. **Casting Votes**
   - Enter the number of voters.
   - Each voter will select their preferred candidate by entering the candidate's corresponding number.

3. **Displaying Results**
   - After all votes are cast, the program will display the candidate with the highest number of votes as the winner.
   - If there's a tie, it will indicate that no candidate has a majority.

4. **Exiting the Program**
   - Choose to exit the program once results are displayed.

## Requirements

- This program requires a C compiler to build and run.

## How to Compile and Run

1. **Compile**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the source code (`voting_system.c`).
   - Compile the program using the appropriate command for your C compiler. For example:
     ```bash
     gcc voting_system.c -o voting_system
     ```

2. **Run**
   - After compiling successfully, run the executable:
     ```bash
     ./voting_system
     ```

