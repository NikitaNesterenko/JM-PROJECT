package jm;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();

    T get(Long id);

    void create(T t);

    void update(T t);

    void delete(Long id);

}
