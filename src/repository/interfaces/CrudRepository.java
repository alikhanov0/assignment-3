package repository.interfaces;

import java.util.List;

public interface CrudRepository<T> {
    T findById(int id);

    List<T> findAll();

    void create(T entity);

    void update(T entity);

    void delete(int id);
}