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

        RoomBase r1 = new OneBedRoom(0, 1, "201", 2);
        roomController.create(r1);

        Student s = new Student(
                0,
                "Aksungkar",
                "Ganiyatov",
                'M',
                java.sql.Date.valueOf(LocalDate.now()),
                "a@mail.com",
                "87000000000");

        studentController.create(s);

        studentController.getAll().forEach(Student::printSummary);
    }
}
