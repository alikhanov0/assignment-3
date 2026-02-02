import controllers.RoomController;
import controllers.StudentController;
import models.RoomBase;
import models.Student;
import repository.JdbcRoomRepository;
import repository.JdbcStudentRepository;
import repository.interfaces.RoomRepository;
import repository.interfaces.StudentRepository;
import service.RoomServiceImpl;
import service.StudentServiceImpl;
import service.interfaces.RoomService;
import service.interfaces.StudentService;
import utils.DatabaseConnection;
import utils.ReflectionUtils;
import utils.SortingUtils;

import java.sql.Connection;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        RoomRepository roomRepository = new JdbcRoomRepository(connection);
        StudentRepository studentRepository = new JdbcStudentRepository(connection);

        RoomService roomService = new RoomServiceImpl(roomRepository);
        StudentService studentService = new StudentServiceImpl(studentRepository, roomRepository);

        RoomController roomController = new RoomController(roomService);
        StudentController studentController = new StudentController(studentService);

        System.out.println("All rooms:");
        List<RoomBase> rooms = roomController.getAll();
        rooms.forEach(RoomBase::printInfo);

        System.out.println("\nAll students:");
        List<Student> students = studentController.getAll();
        students.forEach(Student::printSummary);

        if (!students.isEmpty() && !rooms.isEmpty()) {
            int studentId = students.get(0).getStudentId();
            int roomId = rooms.get(0).getId();

            studentController.assignRoom(studentId, roomId);
        }

        System.out.println("\nStudents after room assignment:");
        studentController.getAll().forEach(Student::printSummary);

        System.out.println("\nRooms sorted by monthly fee (lambda):");
        SortingUtils.sortRoomsByFee(rooms);
        rooms.forEach(RoomBase::printInfo);

        System.out.println("\nStudents sorted by last name (lambda):");
        SortingUtils.sortStudentsByLastName(students);
        students.forEach(Student::printSummary);

        ReflectionUtils.printClassInfo(RoomBase.class);
        ReflectionUtils.printClassInfo(Student.class);
    }
}
