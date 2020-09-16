package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public BrandDto getByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.BrandDto(" +
                "b.id," +
                "b.name)" +
                "FROM Brand AS b " +
                "WHERE b.name = :name", BrandDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public BrandDto getBrandDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.BrandDto(" +
                "b.id," +
                "b.name)" +
                "FROM Brand AS b " +
                "WHERE b.id =: id", BrandDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
