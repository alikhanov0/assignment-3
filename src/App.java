import controllers.RoomController;
import controllers.StudentController;
import models.*;
import repository.JdbcRoomRepository;
import repository.JdbcStudentRepository;
import service.RoomServiceImpl;
import service.StudentServiceImpl;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        var roomRepo = new JdbcRoomRepository(conn);
        var studentRepo = new JdbcStudentRepository(conn);

        var roomService = new RoomServiceImpl(roomRepo);
        var studentService = new StudentServiceImpl(studentRepo);

        var roomController = new RoomController(roomService);
        var studentController = new StudentController(studentService);

        roomController.getAll().forEach(RoomBase::printInfo);
        studentController.getAll().forEach(Student::printSummary);
        studentController.getById(1).setRoom(roomController.getById(1));
        studentController.getAll().forEach(Student::printSummary);
        // Check for errors
    }
}
