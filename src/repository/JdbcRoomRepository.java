package repository;

import models.*;
import repository.interfaces.RoomRepository;
import utils.RoomMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomRepository implements RoomRepository {

    private final Connection connection;

    public JdbcRoomRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public RoomBase findById(int id) {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRoom(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<RoomBase> findAll() {
        List<RoomBase> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                rooms.add(mapRoom(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    @Override
    public void create(RoomBase room) {
        String sql = """
                    INSERT INTO rooms (dorm_id, room_number, floor, room_type)
                    VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, room.getDormId());
            ps.setString(2, room.getRoomNumber());
            ps.setInt(3, room.getFloor());
            ps.setString(4, room.getType());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(RoomBase room) {
        String sql = """
                    UPDATE rooms
                    SET dorm_id = ?, room_number = ?, floor = ?, room_type = ?
                    WHERE room_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, room.getDormId());
            ps.setString(2, room.getRoomNumber());
            ps.setInt(3, room.getFloor());
            ps.setString(4, room.getType());
            ps.setInt(5, room.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM rooms WHERE room_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private RoomBase mapRoom(ResultSet rs) throws SQLException {
        return RoomMapper.map(rs);
    }
}