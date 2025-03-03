package DAO;

import java.util.List;

public interface DAO<T> {

    List<T> findAll();
    void save(T entity);
    void deleteById(Integer id);
    void update(T entity);
    T findById(Integer id);
}