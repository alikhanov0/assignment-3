package service;

import exception.ResourceNotFoundException;
import models.Room;
import repository.DormRepository;
import repository.RoomRepository;

import java.sql.SQLException;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepo = new RoomRepository();
    private final DormRepository dormRepo = new DormRepository();

    public void addRoom(Room r) {
        try {
            if (dormRepo.getById(r.getDormId()) == null)
                throw new ResourceNotFoundException("Dorm not found: " + r.getDormId());

            roomRepo.create(r);
        } catch (SQLException e) {
            throw new RuntimeException("Create room failed", e);
        }
    }

    public List<Room> getAll() {
        try {
            return roomRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Read rooms failed", e);
        }
    }
}
