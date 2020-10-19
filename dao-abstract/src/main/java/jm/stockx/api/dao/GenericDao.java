package jm.stockx.api.dao;

import java.util.List;

public interface GenericDao<T, PK> {
    List<T> getAll();

    T getById(PK id);

    T add(T newInstance);

    T update(T transientObject);

    void deleteById(PK id);

    boolean doesItExistEntity(PK id);
}
