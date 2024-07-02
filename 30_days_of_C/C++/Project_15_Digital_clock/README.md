# Real-Time Clock Display

This C program continuously displays the current time in HH:MM:SS format on the console.

## Description

The program utilizes the `time.h` library to fetch the current system time and `unistd.h` for delaying updates. It continuously updates and displays the time every second.

## How It Works

- **Display Time Function:** The `displayTime()` function retrieves the current time using `time()` and `localtime()` functions from `time.h`. It formats the time into HH:MM:SS format and prints it to the console.
- **Continuous Update:** The main loop in `main()` calls `displayTime()` in a loop using `while(1)`, ensuring the time display is updated every second with the help of `sleep(1)`.

## Requirements

- This program requires a C compiler and POSIX-compliant system to build and run.

## How to Compile and Run

1. **Compile**
   - Open a terminal or command prompt.
   - Navigate to the directory containing the source code (`real_time_clock.c`).
   - Compile the program using the appropriate command for your C compiler. For example:
     ```bash
     gcc real_time_clock.c -o real_time_clock
     ```

2. **Run**
   - After compiling successfully, run the executable:
     ```bash
     ./real_time_clock
     ```

