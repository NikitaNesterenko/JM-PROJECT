package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public Optional<Brand> getByName(String name) {
        Brand brand = entityManager.createQuery("FROM Brand WHERE name = :brandName", Brand.class)
                .setParameter("brandName", name)
                .getSingleResult();
        return Optional.of(brand);
    }

    @Override
    public BrandDto getBrandDtoById(Long id) {
        BrandDto brandDto = entityManager.createQuery("SELECT new jm.stockx.dto.BrandDto(b.name) FROM Brand b where id =: id", BrandDto.class)
                .setParameter("id", id).getSingleResult();
        return brandDto;
    }

}
