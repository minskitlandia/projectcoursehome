package dao;

import java.util.List;

public interface BaseDao<T> {

    void insert(T entity);
    boolean update(T entity);
    void delete(int id);
    T read(int id);
    List<T> read();

}
