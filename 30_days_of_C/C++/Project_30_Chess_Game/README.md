# Chess Game in C++

This is a simple text-based chess game implemented in C++. The game initializes a standard chess board and allows players to make moves by entering coordinates. The game logic currently only handles basic move validation.

## Features
- Initializes a standard chess board with pieces in their starting positions.
- Allows players to input moves.
- Basic move validation (checks if the destination is occupied by the same color).

## Requirements
- C++ compiler (e.g., g++)
- Visual Studio Code (VSCode) with C++ extension

## Setup Instructions

1. **Install a C++ Compiler**:
   - For Windows, you can install MinGW.
   - For macOS, you can use Xcode Command Line Tools.
   - For Linux, you can use `g++` from the package manager.

2. **Install Visual Studio Code**:
   - Download and install VSCode from [here](https://code.visualstudio.com/).

3. **Install the C++ Extension for VSCode**:
   - Open VSCode.
   - Go to the Extensions view by clicking on the Extensions icon in the Activity Bar on the side of the window.
   - Search for "C++" and install the extension provided by Microsoft.

4. **Clone or Download this Repository**:
   - If you have Git installed, you can clone the repository:
     ```bash
     git clone https://github.com/your-username/chess-game-cpp.git
     ```
   - Alternatively, you can download the repository as a ZIP file and extract it.

5. **Open the Project in VSCode**:
   - Open VSCode.
   - Click on `File` > `Open Folder...` and select the folder containing this project.

6. **Compile and Run the Program**:
   - Open the terminal in VSCode by clicking on `Terminal` > `New Terminal`.
   - Compile the program using the following command:
     ```bash
     g++ -o chess main.cpp
     ```
   - Run the compiled program:
     ```bash
     ./chess
     ```

## How to Play
- The board is displayed in the console with pieces represented by characters:
  - `P/p` for White/Black Pawn
  - `R/r` for White/Black Rook
  - `N/n` for White/Black Knight
  - `B/b` for White/Black Bishop
  - `Q/q` for White/Black Queen
  - `K/k` for White/Black King
  - `.` for empty squares
- To make a move, enter the coordinates of the piece you want to move and the destination coordinates. For example:
