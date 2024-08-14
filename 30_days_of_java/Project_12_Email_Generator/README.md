# Email Generator

## Overview
The **Email Generator** is a Java-based application designed to automate the creation and management of company email addresses for new employees. It provides functionalities such as generating random passwords, setting mailbox capacity, and managing alternate emails.

## Features
- **Generate Company Email Addresses:** Automatically generate email addresses in the format `firstname.lastname@department.company.com`.
- **Password Generation:** Create secure random passwords using a combination of uppercase, lowercase, digits, and special characters.
- **Department Selection:** Choose a department for the employee, which will be included in the email address.
- **Mailbox Capacity Management:** Set and get mailbox capacity with a default value of 500MB.
- **Alternate Email Management:** Option to set an alternate email address for the user.
- **Password Management:** Ability to change the password.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) installed on your machine.
- An IDE like Eclipse or IntelliJ IDEA, or simply a text editor with Java support.

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/Email-Generator.git
    ```
2. Navigate to the project directory:
    ```sh
    cd Email-Generator
    ```
3. Compile the Java files:
    ```sh
    javac -d bin src/emailgenerator/*.java
    ```
4. Run the main program:
    ```sh
    java -cp bin emailgenerator.EmailMainProgram
    ```

### Usage
- **When prompted, enter the employee's first and last name.**
- **Select the department by entering the corresponding number (1 for C++, 2 for Java, 3 for Python, 0 for None).**
- **The program will display the generated email and password.**
- **Use the provided methods to manage mailbox capacity, set an alternate email, and change the password.**

## Example
```sh
Welcome!! John Doe. You are Our New Employee. 
Choose Department Codes
1 C++
2 Java
3 Python
0 None
Enter Department Code: 2
Your Password is: aB3!dE4$5G
Display Name: John Doe
Company Email: john.doe@java.xyzemail.com
Mailbox Capacity: 500mb
