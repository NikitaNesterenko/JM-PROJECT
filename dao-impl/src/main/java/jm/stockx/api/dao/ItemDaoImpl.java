package jm.stockx.api.dao;

import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.item.ItemDto;
import jm.stockx.dto.item.ReleaseItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ItemSize;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item, Long> implements ItemDAO {

    @Override
    public ItemDto getItemDtoByItemName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.item.ItemDto(" +
                "i.item.id," +
                "i.item.name," +
                "i.price," +
                "i.releaseDate," +
                "i.condition," +
                "i.description," +
                "i.itemColors" +
                ")" +
                "FROM ItemInfo AS i " +
                "WHERE i.item.name = :name", ItemDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<ItemDto> searchItem(String search, Integer page, Integer size) {
        return entityManager.createQuery("" +
                "FROM ItemInfo i  " +
                "WHERE i.name LIKE '%" + search + "%'", Item.class)
                .setFirstResult(size * (page - 1) + 1)
                .setMaxResults(size)
                .getResultList()
                .stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getMostPopularItemByBrandName(String name) {
        return entityManager.createQuery("" +
                "FROM Item AS i " +
                "INNER JOIN BuyingInfo AS bi " +
                "ON i.id = bi.id " +
                "INNER JOIN Brand AS b " +
                "WHERE b.name =:name " +
                "ORDER BY COUNT(i.id) DESC", Item.class)
                .setParameter("name", name)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Item> getMostPopularItemByStyleId(Long id, int topLimit) {
        return entityManager.createQuery("" +
                "FROM SellingInfo AS si " +
                "LEFT JOIN Item AS i " +
                "ON si.id=i.id " +
                "WHERE i.style = :styleId " +
                "GROUP BY si.id " +
                "ORDER BY count(si.id) DESC", Item.class)
                .setParameter("styleId", id)
                .setMaxResults(topLimit)
                .getResultList();
    }

    @Override
    public List<Item> getNotReleasedItem() {
        return entityManager.createQuery("" +
                "FROM Item AS i " +
                "WHERE i.releaseDate >= GETDATE(current_date)", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> getNotReleasedItemsByBrand(Brand brand) {
        return entityManager.createQuery("" +
                "FROM Item AS i " +
                "WHERE i.releaseDate >= GETDATE(current_date) " +
                "AND i.brand =: brandId", Item.class)
                .setParameter("brandId", brand.getId())
                .getResultList();
    }

    @Override
    public ItemDto getItemDtoByItemId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.item.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.retailPrice," +
                "i.releaseDate," +
                "i.condition," +
                "i.description," +
                "i.itemColors" +
                ")" +
                "FROM Item AS i " +
                "WHERE i.id =: id", ItemDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ItemDto> getItemDtoByColor(String itemColors) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.item.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.retailPrice," +
                "i.releaseDate," +
                "i.condition," +
                "i.description," +
                "i.itemColors" +
                ")" +
                "FROM Item AS i " +
                "WHERE i.itemColors =: itemColors", ItemDto.class)
                .setParameter("itemColors", itemColors)
                .getResultList();
    }

    @Override
    public Item getItemByName(String name) {
        return entityManager.createQuery("" +
                "FROM Item AS i WHERE i.name = : name", Item.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Item getItemById(Long id) {
        return entityManager.createQuery("" +
                "FROM Item AS i WHERE i.id = : id", Item.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void updateItemImageUrl(Long id, String url) {
        entityManager.createQuery("" +
                "UPDATE Item i SET i.itemImageUrl = : url WHERE i.id = : id  ")
                .setParameter("url", url)
                .setParameter("id", id)
                .executeUpdate();
    }


    @Override
    public List<ReleaseItemDto> getReleaseItemDtoByPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
        String sql = "" +
                "SELECT NEW jm.stockx.dto.item.ReleaseItemDto(" +
                "info.item.name," +
                "info.condition, " +
                "info.itemImageUrl, " +
                "info.price, " +
                "info.releaseDate) " +
                "FROM ItemInfo AS info " +
                "WHERE info.releaseDate BETWEEN :begin AND :end";
        return entityManager.createQuery(sql, ReleaseItemDto.class)
                .setParameter("begin", beginningPeriod)
                .setParameter("end", endPeriod)
                .getResultList();
    }
}
