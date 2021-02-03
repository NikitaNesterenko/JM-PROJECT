package jm.stockx.api.dao;

import java.util.List;

public interface GenericDao<T, P> {
    List<T> getAll();

    T getById(P id);

    void add(T newInstance);

    T update(T transientObject);

    void deleteById(P id);

    boolean doesItExistEntity(P id);
}
