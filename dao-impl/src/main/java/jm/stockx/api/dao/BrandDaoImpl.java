package jm.stockx.api.dao;

import jm.stockx.dto.brand.BrandDto;
import jm.stockx.entity.Brand;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BrandDaoImpl extends AbstractDAO<Brand, Long> implements BrandDAO {

    @Override
    public BrandDto getBrandDtoByBrandName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.brand.BrandDto(" +
                "b.id," +
                "b.name)" +
                "FROM Brand AS b " +
                "WHERE b.name = :name", BrandDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public BrandDto getBrandDtoByBrandId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.brand.BrandDto(" +
                "b.id," +
                "b.name)" +
                "FROM Brand AS b " +
                "WHERE b.id =: id", BrandDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Brand getBrandByName(String name) {
        return entityManager.createQuery("" +
                "SELECT b FROM Brand AS b WHERE b.name LIKE : brandName", Brand.class)
                .setParameter("brandName", name)
                .getSingleResult();
    }

    @Override
    public List<BrandDto> getPopularBrands() {
        return entityManager.createQuery("" +
                        "SELECT NEW jm.stockx.dto.brand.BrandDto(" +
                        "b.id," +
                        "b.name" +
                        ") " +
                        "FROM Brand b",
                BrandDto.class)
                .setMaxResults(4)
                .getResultList();
    }

    /**
     * На данный момент возвращает пустой List, так как отсутствуют записи(объекты) в SellingInfo.
     *
     * @return List<BrandDto>
     */
    @Override
    public List<BrandDto> getPopularBrandsInTwoMonths() {
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime date2MonthBack = dateNow.minusMonths(2L);

        return entityManager.createQuery("" +
                        "SELECT NEW jm.stockx.dto.brand.BrandDto(" +
                        "b.id," +
                        "b.name" +
                        ") " +
                        "FROM SellingInfo si " +
                        "JOIN Brand b ON b.id = si.itemInfo.brand.id " +
                        "WHERE si.orderDate <= : dateNow AND si.orderDate >= : date2MonthBack " +
                        "GROUP BY b, si.orderDate " +
                        "ORDER BY count(si.itemInfo) DESC, si.orderDate DESC ",
                BrandDto.class)
                .setParameter("dateNow", dateNow)
                .setParameter("date2MonthBack", date2MonthBack)
                .setMaxResults(4)
                .getResultList();
    }
}
