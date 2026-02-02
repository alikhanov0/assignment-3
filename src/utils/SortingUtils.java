package utils;

import models.RoomBase;
import models.Student;

import java.util.List;

public class SortingUtils {

    public static void sortRoomsByFee(List<RoomBase> rooms) {
        rooms.sort((r1, r2) -> Double.compare(r1.getMonthlyFee(), r2.getMonthlyFee()));
    }

    public static void sortStudentsByLastName(List<Student> students) {
        students.sort((s1, s2) -> s1.getLastName().compareToIgnoreCase(s2.getLastName()));
    }
}
