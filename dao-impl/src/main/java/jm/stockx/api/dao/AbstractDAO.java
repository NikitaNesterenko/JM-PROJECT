package jm.stockx.api.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDAO<T, PK> implements GenericDao<T, PK> {

    @PersistenceContext
    EntityManager entityManager;

    private final Class clazz;

    public AbstractDAO() {
        clazz = (Class) ((java.lang.reflect.ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> getAll() {
        return entityManager.createQuery(
                "FROM " + clazz.getName())
                .getResultList();
    }

    public T getById(PK id) {
        return (T) entityManager.find(clazz, id);
    }

    public void add(T t) {
        entityManager.persist(t);
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public void deleteById(PK id) {
        entityManager.remove(getById(id));
    }

    public boolean doesItExistEntity(Long desiredId) {
        Long existingValue = (Long) entityManager.createQuery(
                "SELECT count(c.id) " +
                        "FROM " + clazz.getName() + " AS c" +
                        "WHERE x.id =" + desiredId)
                .getSingleResult();

        return existingValue > 0;
    }
}
