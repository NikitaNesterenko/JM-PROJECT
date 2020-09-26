package jm.stockx.api.dao;

import jm.stockx.dto.ItemDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemDaoImpl extends AbstractDAO<Item, Long> implements ItemDAO {

    @Override
    public ItemDto getItemDtoByName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.price," +
                "i.retailPrice," +
                "i.lowestAsk," +
                "i.highestBid," +
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
                "FROM Item AS i " +
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
                "FROM SellingInfo AS si " +
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
    public ItemDto getItemDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.price," +
                "i.retailPrice," +
                "i.lowestAsk," +
                "i.highestBid," +
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
    public List<ItemDto> getItemsByColors(String itemColors) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ItemDto(" +
                "i.id," +
                "i.name," +
                "i.price," +
                "i.retailPrice," +
                "i.lowestAsk," +
                "i.highestBid," +
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
                "UPDATE Item AS i SET i.itemImageUrl = : url WHERE i.id = : id  ")
                .setParameter("url", url)
                .setParameter("id", id)
                .executeUpdate();
    }
}
