package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.OneBedRoom;
import models.RoomBase;
import models.ThreeBedRoom;
import models.TwoBedRoom;

public class RoomMapper {
    public static RoomBase map(ResultSet rs) throws SQLException {

        int id = rs.getInt("room_id");
        int dormId = rs.getInt("dorm_id");
        String number = rs.getString("room_number");
        int floor = rs.getInt("floor");
        String type = rs.getString("room_type");

        return switch (type) {
            case "ONE_BED" -> new OneBedRoom(id, dormId, number, floor);
            case "TWO_BED" -> new TwoBedRoom(id, dormId, number, floor);
            case "THREE_BED" -> new ThreeBedRoom(id, dormId, number, floor);
            default -> throw new IllegalArgumentException("Unknown room type: " + type);
        };
    }
}
