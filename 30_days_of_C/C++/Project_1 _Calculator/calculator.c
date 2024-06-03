#include <stdio.h>

int main() {
    char operator;
    double firstNumber, secondNumber;

    printf("Enter an operator (+, -, *, /): ");
    scanf(" %c", &operator);

    printf("Enter the first operand: ");
    scanf("%lf", &firstNumber);

    printf("Enter the second operand: ");
    scanf("%lf", &secondNumber);

    switch(operator) {
        case '+':
            printf("%.1lf + %.1lf = %.1lf\n", firstNumber, secondNumber, firstNumber + secondNumber);
            break;
        case '-':
            printf("%.1lf - %.1lf = %.1lf\n", firstNumber, secondNumber, firstNumber - secondNumber);
            break;
        case '*':
            printf("%.1lf * %.1lf = %.1lf\n", firstNumber, secondNumber, firstNumber * secondName;
            break;
        case '/':
            if(secondNumber != 0.0)
                printf("%.1lf / %.1lf = %.1lf\n", firstNumber, secondNumber, firstNumber / secondNumber);
            else
                printf("Division by zero error!\n");
            break;
        default:
            printf("Error! Operator is not correct\n");
    }

    return 0;
}