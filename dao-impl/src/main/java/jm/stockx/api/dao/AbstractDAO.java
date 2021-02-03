package jm.stockx.api.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDAO<T, P> implements GenericDao<T, P> {

    private final Class<T> clazz;

    @PersistenceContext
    public EntityManager entityManager;

    @SuppressWarnings("unchecked")
    protected AbstractDAO() {
        clazz = (Class<T>) ((java.lang.reflect.ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    public T getById(P id) {
        return entityManager.find(clazz, id);
    }

    public void add(T t) {
        entityManager.persist(t);
    }

    public T update(T t) {
        return entityManager.merge(t);
    }

    public void deleteById(P id) {
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
}
