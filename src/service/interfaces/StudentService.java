package service.interfaces;

import models.Student;
import java.util.List;

public interface StudentService {
    Student getById(int id);

    List<Student> getAll();

    void add(Student student);

    void update(Student student);

    void delete(int id);
}
