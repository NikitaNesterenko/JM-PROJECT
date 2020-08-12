package jm.stockx.api.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDAO<T, PK> implements GenericDao<T, PK> {

    private final Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        clazz = (Class<T>) ((java.lang.reflect.ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    public T getById(PK id) {
        return entityManager.find(clazz, id);
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
        Long existingValue = entityManager.createQuery("" +
                "SELECT COUNT(c.id) " +
                "FROM " + clazz.getName() + " AS c " +
                "WHERE c.id =: desiredId", Long.class)
                .setParameter("desiredId", desiredId)
                .getSingleResult();

        return existingValue > 0;
    }

    public boolean doesItExistEntityByName(Long desiredName) {
        Long existingValue = entityManager.createQuery("" +
                "SELECT COUNT(c.name) " +
                "FROM " + clazz.getName() + " AS c " +
                "WHERE c.name =: desiredName", Long.class)
                .setParameter("desiredName", desiredName)
                .getSingleResult();

        return existingValue > 0;
    }

}
