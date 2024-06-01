
# **RPN Calculator** 📟
 **Overview**

This is a simple Reverse Polish Notation (RPN) calculator implemented in [language/tool]. RPN is a mathematical notation in which operators follow their operands, eliminating the need for parentheses to dictate the order of operations.


## Features 🔢

- Basic arithmetic operations: addition, subtraction, multiplication, and division
- Stack-based calculation
- Supports multi-digit numbers and decimals

## How It Works

The RPN calculator uses a stack to keep track of numbers and operators. When a number is entered, it is pushed onto the stack. When an operator is entered, it pops the required number of operands from the stack, performs the operation, and pushes the result back onto the stack.

- **Supported Operations**

- Addition (+): Pops the top two numbers, adds them, and pushes the result.
- Subtraction (-): Pops the top two numbers, subtracts the second popped from the first, and pushes the result.
- Multiplication (*): Pops the top two numbers, multiplies them, and pushes the result.
- Division (/): Pops the top two numbers, divides the first popped by the second, and pushes the result.



