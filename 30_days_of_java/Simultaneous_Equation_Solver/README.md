# Simultaneous Equations Solver

## Introduction

The `SimultaneousEquationsSolver` program in Java is designed to solve a system of two linear equations with two variables using Cramer's rule. Linear equations are mathematical expressions that represent relationships between variables with constant coefficients. Solving these equations simultaneously means finding values for the variables that satisfy both equations at the same time.

## What the Code Does

The program performs the following steps:

1. **Introduction and User Prompt**: It introduces the user to the program and prompts them to enter the coefficients of the two linear equations.
2. **Input Validation**: It ensures that the user inputs valid numerical values for the coefficients.
3. **Solving the Equations**: It uses Cramer's rule to solve the system of equations. If a unique solution exists, the program calculates and displays the values of the variables \(x\) and \(y\). If no unique solution exists (i.e., if the determinant of the coefficient matrix is zero), the program informs the user.
4. **Output**: It displays the solution in a user-friendly format.

## How It Works

### 1. Introduction and User Prompt

The program starts by printing an introduction and explaining the format of the equations. It then uses the `Scanner` class to prompt the user to enter the coefficients of the two equations.

### 2. Input Validation

The `getDoubleInput` method ensures that the user inputs valid numerical values. If the user enters an invalid input, the method prompts the user again until a valid number is entered.

### 3. Solving the Equations

The core of the solution lies in the `solveEquations` method, which implements Cramer's rule. Here’s how it works:

- **Determinant Calculation**: The program calculates the determinant of the coefficient matrix:
  
  \[
  \text{determinant} = a1 \times b2 - a2 \times b1
  \]


- **Unique Solution Check**: If the determinant is zero, the system does not have a unique solution (it may be either inconsistent or have infinitely many solutions). The program returns `null` in this case.

- **Cramer's Rule**: If the determinant is non-zero, the program calculates the values of \(x\) and \(y\) using the formulas:
  
  \[
  x = \frac{(c1 \times b2 - c2 \times b1)}{\text{determinant}}
  \]
  \[
  y = \frac{(a1 \times c2 - a2 \times c1)}{\text{determinant}}
  \]
  

  The program then returns these values as a double array.

### 4. Output

The solution is displayed in a user-friendly graphical interface. If a unique solution exists, it shows the values of x and y. Otherwise, it informs the user that no unique solution exists.


### Enhancing User Interaction with a Graphical User Interface (GUI)
To enhance usability and make the tool more engaging, we have added a graphical user interface (GUI) using Java. This provides a more intuitive and visually appealing way for users to interact with the equation solver, significantly improving the overall experience.

The GUI features input fields for the coefficients, a solve button, and a display area for the solution, all arranged in an aesthetically pleasing layout. Below is an example of how the modified interface looks:

By integrating a GUI, we make the application more accessible to users who prefer visual interaction over command-line operations. This new interface ensures a seamless and user-friendly experience.

### Here is the glimpse of the Simultaneous Equation Solver:

![image](https://github.com/user-attachments/assets/6395fe26-6644-4a5d-ad3e-23dc02e9bc85)

