package repository;

import models.Student;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public void create(Student s) throws SQLException {
        String sql = """
                    INSERT INTO students(first_name, last_name, gender, enrollment_date, email, phone)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setString(3, String.valueOf(s.getGender()));
            ps.setDate(4, s.getEnrollmentDate());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getPhone());
            ps.executeUpdate();
        }
    }

    public List<Student> getAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Student getById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return map(rs);

            return null;
        }
    }

    public void update(int id, Student s) throws SQLException {
        String sql = """
                    UPDATE students
                    SET first_name=?, last_name=?, gender=?, enrollment_date=?, email=?, phone=?
                    WHERE student_id=?
                """;

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setString(3, String.valueOf(s.getGender()));
            ps.setDate(4, s.getEnrollmentDate());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getPhone());
            ps.setInt(7, id);

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("gender").charAt(0),
                rs.getDate("enrollment_date"),
                rs.getString("email"),
                rs.getString("phone"));
    }
}
