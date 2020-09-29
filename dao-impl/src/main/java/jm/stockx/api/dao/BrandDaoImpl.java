package jm.stockx.api.dao;

import jm.stockx.dto.BrandDto;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public BrandDto getBrandDtoByName(String name) {
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

    @Override
    public Brand getBrand(String name) {
        return entityManager.createQuery("" +
                "select b FROM Brand AS b WHERE b.name LIKE : brandName", Brand.class)
                .setParameter("brandName", name)
                .getSingleResult();
    }

    @Override
    public List<Brand> getPopularBrandIn2Month() {
        LocalDateTime dateNow = LocalDateTime.now();
        return entityManager.createQuery("" +
                "SELECT " +
                "si.item.brand " +
                "FROM SellingInfo si " +
                "WHERE si.orderDate <= : dateNow " +
                "ORDER BY si.orderDate DESC", Brand.class)
                .setParameter("dateNow", dateNow)
                .setMaxResults(5)
                .getResultList();
    }
}
