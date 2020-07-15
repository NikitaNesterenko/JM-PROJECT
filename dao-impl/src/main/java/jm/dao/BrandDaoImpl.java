package jm.dao;

import jm.api.dao.BrandDAO;
import jm.stockx.api.dao.AbstractDAO;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand> implements BrandDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Brand> getBrandByName(String name) {
        try {
            Brand brand = entityManager.createQuery("FROM Brand WHERE name = :brandName", Brand.class)
                    .setParameter("brandName", name)
                    .getSingleResult();
            return Optional.of(brand);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
