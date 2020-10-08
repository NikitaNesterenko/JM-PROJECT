package jm.stockx.api.dao;

import jm.stockx.dto.itemInfo.ItemInfoCardDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ItemInfoDaoImpl extends AbstractDAO<ItemInfo, Long> implements ItemInfoDAO {

    @Override
    public ItemInfo getItemInfoByItemId(Long id) {
//        return entityManager.createQuery(
//                "SELECT i FROM ItemInfo AS i " +
//                        "WHERE i.item.id = :itemId", ItemInfo.class)
//                .setParameter("itemId", itemId)
//                .getSingleResult();
    return null;
    }

    public List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "if.item.name," +
                "if.item.itemImageUrl," +
                "if.lowestAsk" +
                ")" +
                "FROM ItemInfo if " +
                "WHERE if.price > :price", ItemInfoCardDto.class)
                .setParameter("price", price.getAmount())
                .getResultList();
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.itemInfo.ItemInfoCardDto(" +
                "i.item.name," +
                "i.item.itemImageUrl," +
                "i.lowestAsk" +
                ")" +
                "FROM ItemInfo AS i " +
                "WHERE i.item.itemCategory =: itemCategory", ItemInfoCardDto.class)
                .setParameter("itemCategory", category)
                .getResultList();
    }

}


