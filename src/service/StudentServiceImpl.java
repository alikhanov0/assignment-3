package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import models.Student;
import repository.interfaces.StudentRepository;
import service.interfaces.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student getById(int id) {
        Student s = repo.findById(id);
        if (s == null) {
            throw new ResourceNotFoundException("Student not found: " + id);
        }
        return s;
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public void add(Student student) {
        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            throw new InvalidInputException("First name is required");
        }
        repo.create(student);
    }

    @Override
    public void update(Student student) {
        getById(student.getStudentId());
        repo.update(student);
    }

    @Override
    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }
}
