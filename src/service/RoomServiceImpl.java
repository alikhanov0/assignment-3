package service;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import models.RoomBase;
import repository.interfaces.RoomRepository;
import service.interfaces.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository repo;

    public RoomServiceImpl(RoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public RoomBase getById(int id) {
        RoomBase r = repo.findById(id);
        if (r == null) {
            throw new ResourceNotFoundException("Room not found: " + id);
        }
        return r;
    }

    @Override
    public List<RoomBase> getAll() {
        return repo.findAll();
    }

    @Override
    public void add(RoomBase room) {
        if (room.getRoomNumber() == null || room.getRoomNumber().isBlank()) {
            throw new InvalidInputException("Room number is required");
        }
        repo.create(room);
    }

    @Override
    public void update(RoomBase room) {
        getById(room.getId());
        repo.update(room);
    }

    @Override
    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }
}
