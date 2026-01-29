package service.interfaces;

import models.RoomBase;
import java.util.List;

public interface RoomService {
    RoomBase getById(int id);

    List<RoomBase> getAll();

    void add(RoomBase room);

    void update(RoomBase room);

    void delete(int id);
}
