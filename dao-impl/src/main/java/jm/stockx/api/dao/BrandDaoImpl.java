package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public Optional<Brand> getByName(String name) {
        String query =
                "FROM Brand " +
                        "WHERE name = :brandName";
        Brand brand = entityManager.createQuery(query, Brand.class)
                .setParameter("brandName", name)
                .getSingleResult();
        return Optional.of(brand);
    }

    @Override
    public BrandDto getBrandDtoById(Long id) {
        String query =
                "SELECT new jm.stockx.dto.BrandDto(b.id, b.name)" +
                        "FROM Brand AS b " +
                        "WHERE id =: id";

        return entityManager.createQuery(query, BrandDto.class)
                .setParameter("id", id).getSingleResult();
    }
}
