package repository;

import models.Room;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    public void create(Room r) throws SQLException {
        String sql = """
                    INSERT INTO rooms(dorm_id, room_number, floor, room_type)
                    VALUES (?, ?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, r.getDormId());
            ps.setString(2, r.getRoomNumber());
            ps.setInt(3, r.getFloor());
            ps.setString(4, r.getRoomType());
            ps.executeUpdate();
        }
    }

    public List<Room> getAll() throws SQLException {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Room getById(int id) throws SQLException {
        String sql = "SELECT * FROM rooms WHERE room_id=?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return map(rs);

            return null;
        }
    }

    private Room map(ResultSet rs) throws SQLException {
        return new Room(
                rs.getInt("room_id"),
                rs.getInt("dorm_id"),
                rs.getString("room_number"),
                rs.getInt("floor"),
                rs.getString("room_type"));
    }
}
