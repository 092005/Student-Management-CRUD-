import java.sql.*;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";  // Change this if needed
    private static final String PASSWORD = "Diksha*09";  // Replace with your MySQL password

    // Create Student
    public void addStudent(String name, int age, String course) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (name, age, course) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, course);
            stmt.executeUpdate();
            System.out.println("✅ Student Added Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read Students
    public void getStudents() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Student
    public void updateStudent(int id, String name, int age, String course) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE students SET name=?, age=?, course=? WHERE id=?")) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, course);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("✅ Student Updated Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Student Deleted Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
