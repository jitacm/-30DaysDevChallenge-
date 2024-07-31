# OTP Generator

## Overview
The OTP (One-Time Password) Generator project is designed to create a simple yet effective OTP generation and validation system using Java. This project is part of the `30_days_of_java` folder in the `30DaysDevChallenge` repository. The OTP generator can be utilized in various applications to enhance security through two-factor authentication (2FA).

## Features
- **Random OTP Generation:** Generates random OTPs of a specified length.
- **Configurable OTP Length:** Allows users to configure the length of the OTP (e.g., 4, 6, 8 digits).
- **Alphanumeric Support:** Option to generate OTPs containing alphanumeric characters.
- **Validity Period:** Sets a validity period for the OTP, after which it expires.
- **Logging:** Basic logging to keep track of OTP generation and usage.

## Prerequisites
- **Java Development Kit (JDK)**: Install the JDK from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).
- **Maven**: Install Maven from the [Apache Maven website](https://maven.apache.org/download.cgi).
- **Visual Studio Code (VS Code)**: Download and install VS Code from the [official website](https://code.visualstudio.com/).
- **VS Code Extensions**:
  - [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
  - [Test Runner for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test)

## Installation
1. **Clone the Repository**:
    ```sh
    git clone https://github.com/nikhare_priyant/30DaysDevChallenge.git
    cd 30DaysDevChallenge/30_days_of_java/otp-generator
    ```

2. **Open the Project in VS Code**:
    - Open VS Code.
    - Use the `File` > `Open Folder` menu to open the `otp-generator` directory.

3. **Install Necessary Extensions**:
    - Open the Extensions view (`Ctrl+Shift+X`).
    - Search for and install the **Java Extension Pack**.
    - Search for and install **Test Runner for Java**.

4. **Build the Project**:
    - Open the integrated terminal in VS Code (`Ctrl+``).
    - Run the following command:
        ```sh
        mvn clean install
        ```

## Usage
1. **Run the Application**:
    - Open `OtpGenerator.java` in VS Code.
    - Right-click anywhere in the editor and select `Run Java`.

2. **Expected Output**:
    ```
    Generated Numeric OTP: 839502
    Generated Alphanumeric OTP: j8K3ZmQ2
    Is OTP valid? true
    ```

3. **Run Unit Tests**:
    - You can run tests from the Test Explorer view in VS Code.
    - Alternatively, in the terminal, run:
        ```sh
        mvn test
        ```

## Project Structure
otp-generator/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- otp/
|   |   |   |   |   |-- OtpGenerator.java
|   |   |   |   |   |-- OtpValidator.java
|   |-- test/
|       |-- java/
|           |-- com/
|               |-- otp/
|                   |-- OtpGeneratorTest.java
|                   |-- OtpValidatorTest.java
|-- pom.xml
|-- README.md

## Contributing
1. **Fork the repository**.
2. **Create a new branch** (`git checkout -b feature/your-feature`).
3. **Commit your changes** (`git commit -m 'Add some feature'`).
4. **Push to the branch** (`git push origin feature/your-feature`).
5. **Open a pull request**.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments
- [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [JUnit 5](https://junit.org/junit5/)
- [Apache Maven](https://maven.apache.org/)
