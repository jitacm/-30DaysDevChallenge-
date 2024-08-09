# Fee Report System

## Overview
The Fee Report System is a console-based Java application designed to manage and generate detailed fee reports for educational institutions. It includes functionalities for student information management, fee structure definition, fee payment tracking, and report generation.

## Features
- **Student Information Management**
  - Add, update, and delete student details.
  - Store personal and academic information.

- **Fee Structure**
  - Define various fee categories (tuition, examination, library, etc.).
  - Set different fee structures for different courses and programs.

- **Fee Payment Tracking**
  - Record fee payments made by students.
  - Track due and overdue payments.
  - Generate fee receipts.

- **Report Generation**
  - Generate detailed fee reports for individual students.
  - Create summary reports for the entire institution.
  - Export reports in multiple formats (PDF, Excel).

- **User Roles and Access Control**
  - Define roles such as Admin, Accountant, and Student.
  - Set access permissions based on user roles.

- **Notifications**
  - Send payment reminders to students.
  - Notify students and parents about due dates and overdue fees.

## Project Structure
```
30_days_of_java/
│
├── FeeReportSystem.java
├── Main.java
└── Student.java
```

## Getting Started

### Prerequisites
- Java 8+
- IDE or text editor of your choice

### Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/nikharepriyant/30DaysDevChallenge.git
   ```

2. **Navigate to the project directory:**
   ```sh
   cd 30DaysDevChallenge/30_days_of_java
   ```

3. **Compile the Java files:**
   ```sh
   javac Main.java FeeReportSystem.java Student.java
   ```

4. **Run the application:**
   ```sh
   java Main
   ```

## Usage
1. **Add Student:**
   - Enter student ID, name, and course details.

2. **Update Student:**
   - Enter the student ID to update, followed by the new name and course details.

3. **Delete Student:**
   - Enter the student ID to delete.

4. **Add Fee Payment:**
   - Enter the student ID followed by the payment amount.

5. **Generate Fee Report:**
   - Enter the student ID to generate and display their fee report.

## Example
```
Fee Report System
1. Add Student
2. Update Student
3. Delete Student
4. Add Fee Payment
5. Generate Fee Report
6. Exit
Select an option: 1
Enter student ID: 101
Enter student name: Priyant Nikhare
Enter student course: Computer Science
The student added successfully.
```

## Contribution
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any questions or feedback, please contact:
- [Priyant P. Nikhare](mailto:nikharepriyant@gmail.com)
- GitHub: [nikharepriyant](https://github.com/nikharepriyant)
