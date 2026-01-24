package service;

import models.Student;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    public void addStudent(Student s) {
        try {
            repo.create(s);
        } catch (SQLException e) {
            throw new RuntimeException("Create failed", e);
        }
    }

    public List<Student> getAll() {
        try {
            return repo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Read failed", e);
        }
    }

    public Student getById(int id) {
        try {
            Student s = repo.getById(id);
            if (s == null)
                throw new RuntimeException("Student not found");
            return s;
        } catch (SQLException e) {
            throw new RuntimeException("Read by id failed", e);
        }
    }

    public void update(int id, Student s) {
        try {
            if (repo.getById(id) == null)
                throw new RuntimeException("Student not found");
            repo.update(id, s);
        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    public void delete(int id) {
        try {
            if (repo.getById(id) == null)
                throw new RuntimeException("Student not found");
            repo.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }
}
