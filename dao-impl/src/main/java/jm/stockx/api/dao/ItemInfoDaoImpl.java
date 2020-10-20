package jm.stockx.api.dao;

import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.dto.itemInfo.ItemSearchDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ItemInfoDaoImpl extends AbstractDAO<ItemInfo, Long> implements ItemInfoDAO {

    @Override
    public ItemInfo getItemInfoByItemId(Long id) {
        return entityManager.createQuery(
                "SELECT i FROM ItemInfo AS i " +
                        "WHERE i.item.id = :itemId", ItemInfo.class)
                .setParameter("itemId", id)
                .getSingleResult();
    }
    @Override
    public ItemInfo getItemInfoByItemName(String itemName) {
        return entityManager.createQuery(
                "SELECT i FROM ItemInfo AS i " +
                        "WHERE i.item.name = :itemName", ItemInfo.class)
                .setParameter("itemName", itemName)
                .getSingleResult();
    }

    public List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "if.item.name," +
                "if.itemImageUrl," +
                "if.lowestAsk" +
                ")" +
                "FROM ItemInfo if " +
                "WHERE if.price > :price", ItemInfoCardDto.class)
                .setParameter("price", price.getAmount())
                .getResultList();
    }

    @Override
    public List<ItemSearchDto> getItemSearchDtoBySearch(String search) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemSearchDto(i.itemCategory, COUNT(i)) " +
                "FROM ItemInfo i " +
                "WHERE TRIM(LOWER(i.item.name)) LIKE LOWER(CONCAT('%', :search, '%')) " +
                "GROUP BY i.itemCategory " +
                "ORDER BY i.itemCategory DESC " +
                "", ItemSearchDto.class)
                .setParameter("search", search.trim())
                .getResultList();
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "i.item.name," +
                "i.itemImageUrl," +
                "i.lowestAsk" +
                ")" +
                "FROM ItemInfo AS i " +
                "WHERE i.itemCategory =: itemCategory", ItemInfoCardDto.class)
                .setParameter("itemCategory", category)
                .getResultList();
    }

}


