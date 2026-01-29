package controllers;

import models.RoomBase;
import service.interfaces.RoomService;

import java.util.List;

public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    public void create(RoomBase r) {
        service.add(r);
    }

    public RoomBase getById(int id) {
        return service.getById(id);
    }

    public List<RoomBase> getAll() {
        return service.getAll();
    }

    public void update(RoomBase r) {
        service.update(r);
    }

    public void delete(int id) {
        service.delete(id);
    }
}
