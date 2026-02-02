package repository.interfaces;

import models.Student;

public interface StudentRepository extends CrudRepository<Student> {
    void assignRoom(int studentId, int roomId);
}
