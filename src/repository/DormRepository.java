package repository;

import models.Dorm;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DormRepository {

    public void create(Dorm d) throws SQLException {
        String sql = """
                    INSERT INTO dorms(dorm_name, address, capacity)
                    VALUES (?, ?, ?)
                """;

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.getDormName());
            ps.setString(2, d.getAddress());
            ps.setInt(3, d.getCapacity());
            ps.executeUpdate();
        }
    }

    public List<Dorm> getAll() throws SQLException {
        List<Dorm> list = new ArrayList<>();
        String sql = "SELECT * FROM dorms";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Dorm getById(int id) throws SQLException {
        String sql = "SELECT * FROM dorms WHERE dorm_id=?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return map(rs);

            return null;
        }
    }

    public void update(int id, Dorm d) throws SQLException {
        String sql = """
                    UPDATE dorms
                    SET dorm_name=?, address=?, capacity=?
                    WHERE dorm_id=?
                """;

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.getDormName());
            ps.setString(2, d.getAddress());
            ps.setInt(3, d.getCapacity());
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM dorms WHERE dorm_id=?";

        try (Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Dorm map(ResultSet rs) throws SQLException {
        return new Dorm(
                rs.getInt("dorm_id"),
                rs.getString("dorm_name"),
                rs.getString("address"),
                rs.getInt("capacity"));
    }
}
