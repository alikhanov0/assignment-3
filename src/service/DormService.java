package service;

import models.Dorm;
import repository.DormRepository;

import java.sql.SQLException;
import java.util.List;

public class DormService {

    private final DormRepository repo = new DormRepository();

    public void addDorm(Dorm d) {
        try {
            repo.create(d);
        } catch (SQLException e) {
            throw new RuntimeException("Create dorm failed", e);
        }
    }

    public List<Dorm> getAll() {
        try {
            return repo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Read dorms failed", e);
        }
    }

    public Dorm getById(int id) {
        try {
            Dorm d = repo.getById(id);
            if (d == null)
                throw new RuntimeException("Dorm not found");
            return d;
        } catch (SQLException e) {
            throw new RuntimeException("Read dorm by id failed", e);
        }
    }

    public void update(int id, Dorm d) {
        try {
            if (repo.getById(id) == null)
                throw new RuntimeException("Dorm not found");
            repo.update(id, d);
        } catch (SQLException e) {
            throw new RuntimeException("Update dorm failed", e);
        }
    }

    public void delete(int id) {
        try {
            if (repo.getById(id) == null)
                throw new RuntimeException("Dorm not found");
            repo.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Delete dorm failed", e);
        }
    }
}
