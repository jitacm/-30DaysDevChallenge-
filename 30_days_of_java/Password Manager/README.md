# Password Manager

A simple, secure password manager application with a graphical user interface built in Java.

![image](https://github.com/user-attachments/assets/5c139092-6b2f-48b7-a4c5-80deed8157c1)

## Features

- Store encrypted passwords for various accounts
- Generate strong passwords
- Categorize passwords (e.g., work, personal, finance)
- Implement a master password for access
- Graphical User Interface for easy interaction
- Persistent storage of encrypted passwords

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

### Installation

1. Clone the repository:

`git clone <repository-url>`

2. Navigate to the project directory:

`cd password-manager`

3. Compile the Java files:

`javac*.java`

4. Run the application:

`java Main`

## Usage

1. When you first run the application, you'll be prompted to enter a master password. This password will be used to encrypt and decrypt
your stored passwords.
2. Use the GUI to add new passwords, generate strong passwords, and retrieve stored passwords.
3. Your passwords are automatically saved to a file named `passwords.dat` in the application directory.

## Security Notes

- The master password is not stored anywhere. If you forget it, you won't be able to access your stored passwords.
- While the passwords are encrypted, the security of this application is not suitable for storing highly sensitive information. Use at your own risk.
