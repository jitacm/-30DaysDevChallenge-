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

Finally, the program prints the solution to the console. If a unique solution exists, it displays the values of \(x\) and \(y\). Otherwise, it informs the user that no unique solution exists.
