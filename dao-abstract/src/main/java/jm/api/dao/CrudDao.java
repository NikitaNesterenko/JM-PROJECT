package jm.api.dao;

import java.util.List;

public interface CrudDao<T> {

    List<T> getAll();

    T getById(Long id);

    void add(T t);

    T merge(T t);

    void deleteById(Long id);

}
