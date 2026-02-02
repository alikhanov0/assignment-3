package repository;

import models.RoomBase;
import models.Student;
import repository.interfaces.StudentRepository;
import repository.*;
import utils.RoomMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    private final Connection connection;

    public JdbcStudentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Student findById(int id) {

        String sql = """
                    SELECT s.*, r.*
                    FROM students s
                    LEFT JOIN room_assignments ra ON s.student_id = ra.student_id
                    LEFT JOIN rooms r ON ra.room_id = r.room_id
                    WHERE s.student_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapStudent(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        String sql = """
                    SELECT s.*, r.*
                    FROM students s
                    LEFT JOIN room_assignments ra ON s.student_id = ra.student_id
                    LEFT JOIN rooms r ON ra.room_id = r.room_id
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void create(Student s) {
        String sql = """
                    INSERT INTO students (first_name, last_name, gender, enrollment_date, email, phone)
                    VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setString(3, String.valueOf(s.getGender()));
            ps.setDate(4, s.getEnrollmentDate());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student s) {
        String sql = """
                    UPDATE students
                    SET first_name = ?, last_name = ?, gender = ?, enrollment_date = ?, email = ?, phone = ?
                    WHERE student_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setString(3, String.valueOf(s.getGender()));
            ps.setDate(4, s.getEnrollmentDate());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getPhone());
            ps.setInt(7, s.getStudentId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void assignRoom(int studentId, int roomId) {
        String sql = """
                    INSERT INTO room_assignments (student_id, room_id, assigned_date)
                    VALUES (?, ?, CURRENT_DATE)
                    ON CONFLICT (student_id)
                    DO UPDATE SET room_id = EXCLUDED.room_id,
                                  assigned_date = CURRENT_DATE
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, roomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private RoomBase mapRoom(ResultSet rs) throws SQLException {
        return RoomMapper.map(rs);
    }

    private Student mapStudent(ResultSet rs) throws SQLException {
        Student s = new Student(
                rs.getInt("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("gender").charAt(0),
                rs.getDate("enrollment_date"),
                rs.getString("email"),
                rs.getString("phone"));

        int roomId = rs.getInt("room_id");
        if (!rs.wasNull()) {
            RoomBase room = mapRoom(rs);
            s.setRoom(room);
        }

        return s;
    }

}
