package controllers;

import models.Student;
import service.interfaces.StudentService;

import java.util.List;

public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    public void create(Student s) {
        service.add(s);
    }

    public Student getById(int id) {
        return service.getById(id);
    }

    public List<Student> getAll() {
        return service.getAll();
    }

    public void update(Student s) {
        service.update(s);
    }

    public void delete(int id) {
        service.delete(id);
    }
}
