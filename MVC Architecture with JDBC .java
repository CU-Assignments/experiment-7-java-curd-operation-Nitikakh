 1.Model: Student.java
public class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }
}
 2. Controller: StudentController.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getDepartment());
            pstmt.setDouble(4, student.getMarks());
            pstmt.executeUpdate();
        }
    }

    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("Department"), rs.getDouble("Marks")));
            }
        }
        return students;
    }
}
3.  View: Main.java
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentController controller = new StudentController();

        // Adding students
        controller.addStudent(new Student(1, "Alice", "CS", 85.5));
        controller.addStudent(new Student(2, "Bob", "IT", 78.0));

        // Display students
        List<Student> students = controller.getStudents();
        for (Student s : students) {
            System.out.println(s.getStudentID() + " - " + s.getName() + " - " + s.getDepartment() + " - " + s.getMarks());
        }
    }
}
