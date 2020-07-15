package jm.stockx.api.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AbstractDAO<T> {

    @PersistenceContext
    EntityManager entityManager;

    private Class clazz;

    public AbstractDAO() {
        clazz = (Class) ((java.lang.reflect.ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> getAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    public T getById(Long id) {
        return (T) entityManager.find(clazz, id);
    }

    public void add(T t) {
        entityManager.persist(t);
    }

    public T merge(T t) {
        return entityManager.merge(t);
    }

    public void deleteById(Long id) {
        entityManager.remove(getById(id));
    }

    public boolean doesItExistEntity(Long desiredId) {
        Long existingValue = (Long) entityManager.createQuery("select count(id) from "
                + clazz.getName() + " where id =" + desiredId).getSingleResult();
        return existingValue > 0;
    }
}
