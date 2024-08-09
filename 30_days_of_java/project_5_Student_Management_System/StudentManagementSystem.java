import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Student {
    private int id;
    private String name;
    private String email;

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Email=" + email + "]";
    }
}

class Course {
    private String courseName;
    private List<Student> students;

    public Course(String courseName) {
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return "Course [Name=" + courseName + ", Students=" + students + "]";
    }
}

public class StudentManagementSystem {
    private Map<Integer, Student> students;
    private Map<String, Course> courses;

    public StudentManagementSystem() {
        students = new HashMap<>();
        courses = new HashMap<>();
    }

    public void addStudent(int id, String name, String email) {
        Student student = new Student(id, name, email);
        students.put(id, student);
    }

    public void removeStudent(int id) {
        students.remove(id);
    }

    public void addCourse(String courseName) {
        Course course = new Course(courseName);
        courses.put(courseName, course);
    }

    public void enrollStudentInCourse(int studentId, String courseName) {
        Student student = students.get(studentId);
        Course course = courses.get(courseName);
        if (student != null && course != null) {
            course.addStudent(student);
        }
    }

    public void unenrollStudentFromCourse(int studentId, String courseName) {
        Student student = students.get(studentId);
        Course course = courses.get(courseName);
        if (student != null && course != null) {
            course.removeStudent(student);
        }
    }

    public void displayStudentInfo(int studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    public void displayCourseInfo(String courseName) {
        Course course = courses.get(courseName);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course not found");
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();

        sms.addStudent(1, "Alice", "alice@example.com");
        sms.addStudent(2, "Bob", "bob@example.com");

        sms.addCourse("Math");
        sms.addCourse("Science");

        sms.enrollStudentInCourse(1, "Math");
        sms.enrollStudentInCourse(2, "Science");

        sms.displayStudentInfo(1);
        sms.displayStudentInfo(2);

        sms.displayCourseInfo("Math");
        sms.displayCourseInfo("Science");

        sms.unenrollStudentFromCourse(1, "Math");

        sms.displayCourseInfo("Math");
    }
}
