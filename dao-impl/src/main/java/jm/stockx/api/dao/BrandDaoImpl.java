package jm.stockx.api.dao;

import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public Optional<Brand> getByName(String name) {
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
