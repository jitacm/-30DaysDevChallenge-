#include<stdio.h>
int main(){
    int score1, score2, score3, score4, score5, score6, score7, score8, score9, score10,  score, i, ans;
        printf("Click 1 to start the Game.\nClick 0 to quit the Game.\n");
        scanf("%d", &i);
        while(i != 1 && i != 0){
            printf("Invalid Choice, please try again....\n");
            printf("Click 1 to start the Game.\nClick 0 to quit the Game.\n");
            scanf("%d", &i);
        }
        if(i == 1){
            printf("Starting the Game...\n\n");
        }
        else if(i == 0){
            printf("You quit the Game"); 
        }
        if(i == 1){
            printf("1. What is the corect syntax to declare the pointer to an integer in C ?\n");
            printf("1) int p;\n2) int *p;\n3) int p*;\n4) int &p;\n");
            scanf("%d", &ans);
            if(ans == 2){
                score1 = 2;
                printf("Correct answer...The answer is, int *p\nYou scored 2 points;\n\n");
            }
            else{
                score1 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("2. Which of the following is not valid variable in C ?\n");
            printf("1) int _var;\n2) int var123;\n3) int 123var;\n4) int var_123;\n");
            scanf("%d", &ans);
            if(ans == 3){
                score2 = 2;
                printf("Correct answer...The answer is, int 123var\nYou scored 2  points;\n\n");
            }
            else{
                score2 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("3. What will be the output of following code snippet ?\n");
            printf("#include<stdio.h>\nint main(){\n\tint a = 10;\n\tint b = 20;\n\tint *p = &a;\n\t*p = 30;\n\tp = &b;\n\t*p = 40;\n\tprintf(a, b);\n\treturn 0;\n}\n");
            printf("1) 30 20;\n2) 10 40;\n3) 30 40;\n4) 40 30;\n");
            scanf("%d", &ans);
            if(ans == 3){
                score3 = 2;
                printf("Correct answer...The answer is, 30 40\nYou scored 2  points;\n\n");
            }
            else{
                score3 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("4. What does the 'sizeof' operator return in C ?\n");
            printf("1) The number of elements in an array\n2) The address of variable\n3) The length of string\n4) The size of variable\n");
            scanf("%d", &ans);
            if(ans == 4){
                score4 = 2;
                printf("Correct answer...The answer is, The size of variable\nYou scored 2  points;\n\n");
            }
            else{
                score4 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("5. Which of the following is the correct way to dynamically allocate memory for an array of 10 integers in C ?\n");
            printf("1) int arr[10];\n2) int *arr = (int *)malloc(10 * sizeof(int));\n3) int arr = (int)malloc(10 * sizeof(int));\n4) int *arr = (int *)malloc(10);\n");
            scanf("%d", &ans);
            if(ans == 2){
                score5 = 2;
                printf("Correct answer...The answer is, int *arr = (int *)malloc(10 * sizeof(int))\nYou scored 2  points;\n\n");
            }
            else{
                score5 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("6. What is the purpose of the return statement in a function in C ?\n");
            printf("1) To output a value\n2) To exit a loop;\n3) To terminate the function and optionally return a value;\n4) To skip the current iteration of a loop\n");
            scanf("%d", &ans);
            if(ans == 3){
                score6 = 2;
                printf("Correct answer...The answer is, To terminate the function and optionally return a value;\nYou scored 2  points;\n\n");
            }
            else{
                score6 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("7. Which of the following is a valid if statement in C ?\n");
            printf("1) if (a == 1);\n2) if (a = 1);\n3) if (a : 1);\n4) if (a & 1);\n");
            scanf("%d", &ans);
            if(ans == 1){
                score7 = 2;
                printf("Correct answer...The answer is, if (a == 1)\nYou scored 2  points;\n\n");
            }
            else{
                score7 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("8.  Which of the following keywords is used to define a constant variable in C ?\n");
            printf("1) constant\n2) final\n3) immutable;\n4) const\n");
            scanf("%d", &ans);
            if(ans == 4){
                score8 = 2;
                printf("Correct answer...The answer is, const\nYou scored 2  points;\n\n");
            }
            else{
                score8 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("9.  Which of the following valid declaration in C ?\n");
            printf("1) int function(int a, int b);\n2) int function a, b;\n3) function(int a, int b) int;\n4) int function(a, b) int;\n");
            scanf("%d", &ans);
            if(ans == 1){
                score9 = 2;
                printf("Correct answer...The answer is, int function(int a, int b);\nYou scored 2  points;\n\n");
            }
            else{
                score9 = 0;
                printf("Wrong answer...\n\n");
            }
            printf("10.  What does the continue statement do in a loop in C ?\n");
            printf("1) Terminates the loop\n2) Skips the rest of the code in the loop and starts the next iteration\n3) Exits the function\n4) Jumps to the end of the loop\n");
            scanf("%d", &ans);
            if(ans == 2){
                score10 = 2;
                printf("Correct answer...The answer is, Skips the rest of the code in the loop and starts the next iteration\nYou scored 2  points;\n\n");
            }
            else{
                score10 = 0;
                printf("Wrong answer...\n\n");
            }
        score = score1 + score2 + score3 + score4 + score5 + score6 + score7 + score8 + score9 + score10;
        printf("Total scored is %d out of 20", score);
        }
     return 0;   
}