import models.Dorm;
import models.Student;
import service.DormService;
import service.StudentService;

import java.sql.Date;

public class App {
    public static void main(String[] args) {

        StudentService service = new StudentService();
        DormService dormService = new DormService();

        Dorm d1 = new Dorm(3, "Dorm A", "Abay street 10", 300);
        // dormService.addDorm(d1);

        Student s1 = new Student(
                9,
                "Yerbol",
                "Alikhan",
                'M',
                Date.valueOf("2018-09-01"),
                "alikhanov@gmail.com",
                "87058751277");

        // service.addStudent(s1);

        // roomService.addRoom(r);

        /*
         * System.out.println("Dorms:");
         * for (Dorm d : dormService.getAll()) {
         * System.out.println(d.getDormId() + " " + d.getDormName());
         * }
         * 
         */
        System.out.println("All students:");
        for (Student s : service.getAll()) {
            System.out.println(
                    s.getStudentId() + " " +
                            s.getFirstName() + " " +
                            s.getLastName() + " " +
                            s.getEmail() + " " + s.getEnrollmentDate());
        }

        /*
         * System.out.println("Rooms:");
         * for (Room x : roomService.getAll()) {
         * System.out.println(x.getRoomId() + " Dorm=" + x.getDormId() + " " +
         * x.getRoomNumber());
         * }
         */
    }
}
