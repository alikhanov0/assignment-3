package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import models.RoomBase;
import models.Student;
import repository.interfaces.RoomRepository;
import repository.interfaces.StudentRepository;
import service.interfaces.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final RoomRepository roomRepo;
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo, RoomRepository roomRepo) {
        this.repo = repo;
        this.roomRepo = roomRepo;
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

    @Override
    public void assignRoom(int studentId, int roomId) {

        Student student = getById(studentId);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found: " + studentId);
        }

        RoomBase room = roomRepo.findById(roomId);
        if (room == null) {
            throw new ResourceNotFoundException("Room not found: " + roomId);
        }

        repo.assignRoom(studentId, roomId);
    }

}
