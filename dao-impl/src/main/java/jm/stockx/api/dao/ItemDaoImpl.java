package jm.stockx.api.dao;

import jm.stockx.dto.ItemDto;
import jm.stockx.dto.ReleaseItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import jm.stockx.enums.ItemDirection;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item, Long> implements ItemDAO {

    @Override
    public ItemDto getItemDtoByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.retailPrice," +
                "i.releaseDate," +
                "i.condition," +
                "i.description," +
                "i.itemColors" +
                ")" +
                "FROM Item AS i " +
                "WHERE i.name = :itemName", ItemDto.class)
                .setParameter("itemName", name)
                .getSingleResult();
    }

    @Override
    public List<ItemDto> searchItem(String search, Integer page, Integer size) {
        return entityManager.createQuery("" +
                "FROM Item i  " +
                "WHERE i.name LIKE '%" + search + "%'", Item.class)
                .setFirstResult(size * (page - 1) + 1)
                .setMaxResults(size)
                .getResultList()
                .stream()
                .map(ItemDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getMostPopularItems(String brand) {
        return entityManager.createQuery("" +
                "SELECT i " +
                "FROM Item i " +
                "INNER JOIN BuyingInfo AS bi " +
                "ON i.id = bi.id " +
                "INNER JOIN Brand AS b " +
                "WHERE b.name =:brand " +
                "ORDER BY COUNT(i.id) DESC", Item.class)
                .setParameter("brand", brand)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public List<Item> getTopItemsByStyleFromSellingInfo(Long styleId, int topLimit) {
        return entityManager.createQuery("" +
                "SELECT i " +
                "FROM SellingInfo si " +
                "LEFT JOIN Item AS i " +
                "ON si.id=i.id " +
                "WHERE i.style = :styleId " +
                "GROUP BY si.id " +
                "ORDER BY count(si.id) DESC", Item.class)
                .setParameter("styleId", styleId)
                .setMaxResults(topLimit)
                .getResultList();
    }

    @Override
    public List<Item> getNotReleasedItems() {
        LocalDateTime dateNow = LocalDateTime.now();
        return entityManager.createQuery("" +
                "SELECT i " +
                "FROM Item i " +
                "WHERE i.releaseDate >= : dateNow ", Item.class)
                .setParameter("dateNow", dateNow)
                .getResultList();
    }

    @Override
    public List<Item> getNotReleasedItemsByBrand(Brand brand) {
        LocalDateTime dateNow = LocalDateTime.now();
        return entityManager.createQuery("" +
                "SELECT i " +
                "FROM Item i " +
                "WHERE i.releaseDate >= : dateNow " +
                "AND i.brand =: brandId", Item.class)
                .setParameter("dateNow", dateNow)
                .setParameter("brandId", brand.getId())
                .getResultList();
    }

    @Override
    public ItemDto getItemDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.retailPrice," +
                "i.releaseDate," +
                "i.condition," +
                "i.description," +
                "i.itemColors" +
                ")" +
                "FROM Item i " +
                "WHERE i.id =: id", ItemDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ItemDto> getItemsByColors(String itemColors) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.retailPrice," +
                "i.releaseDate," +
                "i.condition," +
                "i.description," +
                "i.itemColors" +
                ")" +
                "FROM Item i " +
                "WHERE i.itemColors =: itemColors", ItemDto.class)
                .setParameter("itemColors", itemColors)
                .getResultList();
    }

    @Override
    public Item getItemByName(String name) {
        return entityManager.createQuery("" +
                "SELECT i " +
                "FROM Item i WHERE i.name = : name", Item.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Item getItemById(Long id) {
        return entityManager.createQuery("" +
                "SELECT i " +
                "FROM Item i WHERE i.id = : id", Item.class)
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
    public List<Item> searchItem(String search) {
        return entityManager.createQuery("" +
                "SELECT i FROM Item i " +
                "WHERE LOWER(i.name)  LIKE LOWER(CONCAT(: search, '%'))", Item.class)
                .setParameter("search", search)
                .getResultList();
    }

    @Override
    public Long getCountItemByItemDirection(String search, ItemDirection itemDirection) {
        return entityManager.createQuery("" +
                "SELECT count(i) FROM Item i " +
                "JOIN ItemInfo ii ON i.id = ii.item.id " +
                "WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', : search, '%')) " +
                "AND ii.itemDirection = : itemDirection " +
                "GROUP BY ii.itemDirection" +
                "", Long.class)
                .setParameter("search", search)
                .setParameter("itemDirection", itemDirection)
                .getSingleResult();
    }

    @Override
    public List<ItemDirection> getItemDirection(String search) {
        return entityManager.createQuery("" +
                "SELECT ii.itemDirection FROM ItemInfo ii " +
                "JOIN Item i ON i.id = ii.item.id " +
                "WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', : search, '%')) " +
                "GROUP BY ii.itemDirection" +
                "", ItemDirection.class)
                .setParameter("search", search)
                .getResultList();
    }

    @Override
    public Map<ItemDirection, Long> getMap(String search) {
        Map<ItemDirection, Long> map = new TreeMap<>();
        List<ItemDirection> directions = getItemDirection(search);

        for (ItemDirection id : directions) {
            Long aLong = getCountItemByItemDirection(search, id);
            map.put(id, aLong);
        }

        return map;
    }

    @Override
    public List<ReleaseItemDto> getReleaseItemsByPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
        String sql = "" +
                "SELECT NEW jm.stockx.dto.ReleaseItemDto(" +
                "i.id," +
                "i.name," +
                "i.condition, " +
                "i.itemImageUrl, " +
                "i.retailPrice, " +
                "i.releaseDate) " +
                "FROM Item AS i " +
                "WHERE i.releaseDate BETWEEN :begin AND :end";
        return entityManager.createQuery(sql, ReleaseItemDto.class)
                .setParameter("begin", beginningPeriod)
                .setParameter("end", endPeriod)
                .getResultList();
    }
}
