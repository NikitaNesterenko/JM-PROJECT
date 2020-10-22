package jm.stockx.api.dao;

import jm.stockx.entity.Bid;

import java.util.List;
import java.util.Set;

public interface GenericDao<T, PK> {
    List<T> getAll();

    T getById(PK id);

    void add(T newInstance);

    T update(T transientObject);

    void deleteById(PK id);

    boolean doesItExistEntity(PK id);
}
