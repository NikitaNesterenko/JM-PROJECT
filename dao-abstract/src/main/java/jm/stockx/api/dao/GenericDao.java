package jm.stockx.api.dao;

import jm.stockx.entity.Bid;

import java.util.Set;

public interface GenericDao<T, PK> {
    Set<T> getAll();

    T getById(PK id);

    T add(T newInstance);

    T update(T transientObject);

    void deleteById(PK id);

    boolean doesItExistEntity(PK id);
}
