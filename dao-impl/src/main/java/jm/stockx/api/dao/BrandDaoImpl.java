package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.dto.BrandPostDto;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public Optional<Brand> getByName(String name) {
        Brand brand = entityManager.createQuery("" +
                "FROM Brand AS b " +
                "WHERE b.name = :brandName", Brand.class)
                .setParameter("brandName", name)
                .getSingleResult();
        return Optional.of(brand);
    }

    @Override
    public BrandDto getBrandDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.BrandDto(" +
                "b.id," +
                "b.name)" +
                "FROM Brand AS b " +
                "WHERE id =: id", BrandDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
